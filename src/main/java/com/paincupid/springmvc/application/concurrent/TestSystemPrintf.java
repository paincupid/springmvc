package com.paincupid.springmvc.application.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;



public class TestSystemPrintf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("Cleaner: %s\n","ddd");
		Date d1 =new Date();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date d2 =new Date();
		System.out.println(d2.getTime() - d1.getTime());
		
	}

}
