/*package com.paincupid.springmvc.application.log;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.oplog.collector.client.Oplog;
import com.alibaba.oplog.collector.client.OplogClient;
import com.alibaba.oplog.collector.client.enums.DeployMode;
import com.tmall.linlang.biz.conf.LinlangConfigHolder;
import com.tmall.linlang.biz.env.EnvironmentSniffer;

*//**
 * @description
 * @author yuanzhen.lzq
 * Mar 23, 2015 2:17:29 PM
 *//*
@Slf4j
public class OpLoggerHolder {
	private static final String systemId = "OPLOG00804";//流程申请获得
	private static final String systemName = "OPLOG00804_a27538";//流程申请获得
	private static OplogClient client = null;
	static{
        String oplogDir = "c:\\log-lee";

        try{
	        client = new OplogClient(systemId, systemName, deployMode, oplogDir);
	        client.setEncoding("UTF-8");//设置日志编码。
        }catch(Exception e){
        	throw new RuntimeException("init oplog fail.", e);
        }
    }
	public static boolean log(Oplog oplogItem){
		try{
			client.log(oplogItem);
		}catch(Exception e){
			log.error("oplog client log exception, logitem:"+oplogItem, e);
			return false;
		}
		return true;
	}
}
*/