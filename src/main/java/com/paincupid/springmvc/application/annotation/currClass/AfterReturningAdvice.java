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
	
	//标注该方法体为后置通知，当目标方法执行成功后执行该方法体   
	//@AfterReturning("within(com.paincupid.springmvc.json.controller..*) && @annotation(logger)")
	public void insertLogSuccess(JoinPoint jp, OpLogger logger){   
		System.out.println("-------------===-------------------");
	     String moduleName = logger.id();  
	     String signature = jp.getSignature().toString(); //获取目标方法签名   
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
		// 日志动作
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
			// 获取被注解方法的参数，实现动态注解
			String logArg = null;
			if (args.length == 1) {// 用于处理做delete之类需要传入id的操作
				if (args[0].getName() == "java.lang.Long") {
					logArg = String.valueOf((joinPoint.getArgs())[0]);
				} else if (args[0] == String[].class) {
					String[] arrayArg = (String[]) (joinPoint.getArgs())[0];
					logArg = Arrays.toString(arrayArg);
				}
			} else {// 用户被拦截的方法参数是实体的情况，主要是add和update之类的操作，本demo只考虑add和update操作，其他功能需修改代码
				for (int j = 0; j < args.length; j++) {
					if (args[j].getName().startsWith("com.xxx.leopard.model")) {
						Method m = args[j].getMethod("getId");
						String id = String.valueOf(m.invoke(joinPoint.getArgs()[j]));
						extra = id == null ? "新增了" : "修改了id=" + id + "的";
					}
				}
			}
			// 将注解中%转换为被拦截方法参数中的id
			operateDescribe = operateDescribe.indexOf("%") != -1 ? operateDescribe.replace("%", logArg)
					: operateDescribe;
			System.out.println("添加在方法前面的描述是:" + extra + operateDescribe);
			// 以下是数据库操作
		}
	}
}