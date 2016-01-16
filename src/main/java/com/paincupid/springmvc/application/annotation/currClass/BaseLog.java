package com.paincupid.springmvc.application.annotation.currClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseLog {
	
	public void logger(){
		
		Class clazz = this.getClass();
		String annId = "";
		try {
			Object obj = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
			Method[] methods = clazz.getDeclaredMethods();
			
			StackTraceElement[] stacks = new Exception().getStackTrace();
			String methodName = stacks[1].getMethodName();
			System.out.println("methodName: "+ methodName);
			
			for (Method method : methods) {
				
				if(method.isAnnotationPresent(OpLogger.class) && method.getName().equals(methodName)){
					OpLogger logger = method.getAnnotation(OpLogger.class);
					annId = logger.id();
					String clazzName = clazz.getName();
					System.out.println("clazzName: "+clazzName + ", methodName: "+methodName +", annId: "+annId);
				}
			}
			
			
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
