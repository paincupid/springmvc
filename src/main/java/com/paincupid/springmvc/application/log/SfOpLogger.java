/*package com.paincupid.springmvc.application.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.lp.share.web.user.session.SessionUser;
import com.tmall.linlang.oplog.domain.OpCompareVal;
import com.tmall.linlang.oplog.domain.OpNameEnum;
import com.tmall.linlang.oplog.domain.OpTypeEnum;
import com.tmall.linlang.oplog.domain.SfLogItem;
import com.tmall.linlang.oplog.executer.SfOpLogExecuter;

*//**
 * @description
 * @author yuanzhen.lzq
 * Mar 23, 2015 11:24:57 AM
 *//*
@Slf4j
public class SfOpLogger {
	private static final int LOG_EXECUTER_NUM = 5;
	private static final int LOG_QUEUE_MAX_NUM = 2000;
	private static final ExecutorService logThreadPool = Executors.newFixedThreadPool(LOG_EXECUTER_NUM);
	private static BlockingQueue<SfLogItem> logBlockingQueue = new LinkedBlockingDeque<SfLogItem>(LOG_QUEUE_MAX_NUM);
	static{
		for(int i=0;i<LOG_EXECUTER_NUM; i++){
			SfOpLogExecuter executer = new SfOpLogExecuter(logBlockingQueue);
			logThreadPool.submit(executer);
		}
	}
	public static void log(SessionUser sessionUser, String clientIp, OpTypeEnum type, OpNameEnum opName, String opItem, String opItemId){
		SfLogItem item = new SfLogItem(sessionUser, clientIp, type, opName.getName(), opItem, opItemId);
		log(item);
	}
	public static void log(SessionUser sessionUser, OpNameEnum opName, String opItemName){
		SfLogItem item = new SfLogItem(sessionUser, null, OpTypeEnum.NORMAL, opName.getName(), opItemName, opItemName);
		log(item);
	}
	public static void logWithCompare(SessionUser sessionUser, OpNameEnum opName, String opItemName, OpCompareVal compareVal){
		SfLogItem item = new SfLogItem(sessionUser, null, OpTypeEnum.NORMAL, opName.getName(), opItemName, opItemName);
		item.putCompare(compareVal);
		log(item);
	}
	public static void log(SfLogItem info){
		if(info==null){
			return ;
		}
		boolean succ = logBlockingQueue.offer(info);
		if(!succ){
			log.error("offer oplog fail, info:"+info);
		}
	}
	public static void main(String[] args) {
		SfLogItem item = new SfLogItem();
		log(item);
	}
}
*/