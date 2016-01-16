package com.paincupid.springmvc.application.poolv2;

import java.util.Deque;

public class GetID  implements Runnable {
	
	private GenPool pool;
	public GetID(GenPool pool){
		this.pool = pool;
	}
	
	@Override
	public void run() {
		for (int i=0; i<1000; i++){
			Integer it = pool.get();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

}
