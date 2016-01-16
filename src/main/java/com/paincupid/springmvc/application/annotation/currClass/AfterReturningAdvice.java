package com.paincupid.springmvc.application.annotation.currClass;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class AfterReturningAdvice {
	
	//��ע�÷�����Ϊ����֪ͨ����Ŀ�귽��ִ�гɹ���ִ�и÷�����   
	//@AfterReturning("within(com.paincupid.springmvc.json.controller..*) && @annotation(logger)")
	public void insertLogSuccess(JoinPoint jp, OpLogger logger){   
		System.out.println("-------------===-------------------");
	     String moduleName = logger.id();  
	     String signature = jp.getSignature().toString(); //��ȡĿ�귽��ǩ��   
	     String methodName = signature.substring(signature.lastIndexOf("." )+1, signature.indexOf("("));   
	       
	}
	
	
	public void writeLogInfo(JoinPoint joinPoint, OpLogger opLogger) throws Exception, IllegalAccessException {
		System.out.println("--------------------------------");
		// SysUser sysUser =
		// (SysUser)ServletActionContext.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// System.out.println(joinPoint.getStaticPart().toShortString());
		// System.out.println(joinPoint.getStaticPart());
		String temp = joinPoint.getStaticPart().toShortString();
		
		String longTemp = joinPoint.getStaticPart().toLongString();
		String classType = joinPoint.getTarget().getClass().getName();
		String methodName = temp.substring(10, temp.length() - 1);
		Class<?> className = Class.forName(classType);
		// ��־����
		String extra = null;
		@SuppressWarnings("rawtypes")
		Class[] args = new Class[joinPoint.getArgs().length];
		String[] sArgs = (longTemp.substring(longTemp.lastIndexOf("(") + 1, longTemp.length() - 2)).split(",");
		for (int i = 0; i < args.length; i++) {
			if (sArgs[i].endsWith("[]")) {
				args[i] = Array.newInstance(Class.forName("java.lang.String"), 1).getClass();
			} else {
				args[i] = Class.forName(sArgs[i]);
			}
		}
		Method method = className.getMethod(methodName.substring(methodName.indexOf(".") + 1, methodName.indexOf("(")),
				args);
		if (method.isAnnotationPresent(OpLogger.class)) {
			OpLogger logAnnotation = method.getAnnotation(OpLogger.class);
			String operateDescribe = logAnnotation.id();
			// ��ȡ��ע�ⷽ���Ĳ�����ʵ�ֶ�̬ע��
			String logArg = null;
			if (args.length == 1) {// ���ڴ�����delete֮����Ҫ����id�Ĳ���
				if (args[0].getName() == "java.lang.Long") {
					logArg = String.valueOf((joinPoint.getArgs())[0]);
				} else if (args[0] == String[].class) {
					String[] arrayArg = (String[]) (joinPoint.getArgs())[0];
					logArg = Arrays.toString(arrayArg);
				}
			} else {// �û������صķ���������ʵ����������Ҫ��add��update֮��Ĳ�������demoֻ����add��update�����������������޸Ĵ���
				for (int j = 0; j < args.length; j++) {
					if (args[j].getName().startsWith("com.xxx.leopard.model")) {
						Method m = args[j].getMethod("getId");
						String id = String.valueOf(m.invoke(joinPoint.getArgs()[j]));
						extra = id == null ? "������" : "�޸���id=" + id + "��";
					}
				}
			}
			// ��ע����%ת��Ϊ�����ط��������е�id
			operateDescribe = operateDescribe.indexOf("%") != -1 ? operateDescribe.replace("%", logArg)
					: operateDescribe;
			System.out.println("����ڷ���ǰ���������:" + extra + operateDescribe);
			// ���������ݿ����
		}
	}
}