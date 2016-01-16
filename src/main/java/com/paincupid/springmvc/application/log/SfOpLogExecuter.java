/*package com.paincupid.springmvc.application.log;

public class SfOpLogExecuter extends Thread{
	
	private BlockingQueue<SfLogItem> logBlockingQueue;
	
	@Override
	public void run() {
		while (!interrupted()) {
			try {
				Thread.sleep(5);
				SfLogItem logElement = logBlockingQueue.take();
				execute(logElement);
			}catch(Exception e){
				log.error("oplog execute error.", e);
			}
		}
	}
	private void execute(SfLogItem item){
		if(item==null){
			return;
		}
		Oplog oplog = OpLogAdapter.convert(item);
        OpLoggerHolder.log(oplog);
	}
	public SfOpLogExecuter(BlockingQueue<SfLogItem> logBlockingQueue){
		this.logBlockingQueue = logBlockingQueue;
	}
}*/