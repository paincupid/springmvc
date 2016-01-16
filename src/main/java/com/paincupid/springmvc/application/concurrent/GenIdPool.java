package com.paincupid.springmvc.application.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GenIdPool {
	private ExecutorService exec;
	private int cpuCoreNumber;
	private List<Future<String>> tasks = new  ArrayList<Future<String>>();
	
	public GenIdPool() {  
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();  
        //System.out.println("cpuCoreNumber:"+cpuCoreNumber);
        exec = Executors.newFixedThreadPool(cpuCoreNumber);  
    }  
	
	class GenSpeciId implements Callable<String>{
		private String id;
		private List<String> listId = new ArrayList<String>();
		public GenSpeciId(String id){
			this.id = id;
		}
		@Override
		public String call() throws Exception {
			String temp = id.substring(1, id.length());
			int num = new Integer(temp);
			if(listId.size()<5){
				//System.out.println("listId.size(): "+listId.size());
				for(int i=0;i<30;i++){
					listId.add("L"+num);
					num+=1;
				}
				//System.out.println("listId.size(): "+listId.size());
				id = "L"+num;
			}
			Lock lock = new ReentrantLock();
			String res;
			try{
				res = listId.get(0);
				listId.remove(0);
			}finally{
				lock.unlock();
			}
			return res;
		}
		
	}
	public String getId(final String id) throws InterruptedException, ExecutionException{
		for(int i=0;i<cpuCoreNumber;i++){
			GenSpeciId gsi = new GenSpeciId(id);
			FutureTask<String> task = new FutureTask<String>(gsi);  
            if (!exec.isShutdown()) {  
                exec.submit(task);  
            }  
            tasks.add(task); 
		}
		System.out.println(tasks.size());
		for(Future f :tasks){
			System.out.println("id: "+f.get().toString());
		}
		return ((Future)(tasks.get(0))).get().toString();
	}
	public void close() {  
        exec.shutdown();  
    }  
	
	public static void main(String[] args) {
		GenIdPool pool = new GenIdPool();
		try {
			String id = pool.getId("L"+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			pool.close();
		}
		
		

	}

}
