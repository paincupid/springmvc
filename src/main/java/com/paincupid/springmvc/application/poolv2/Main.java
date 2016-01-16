package com.paincupid.springmvc.application.poolv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		deque.add(new Integer(1));
		GenPool gp = new GenPool(deque);
		
		Thread getIdThread = new Thread(new GetID(gp));
		Thread setIdThread = new Thread(new SetID(gp));
		//设置生成Id的为守护线程，当GetID到1000后，守护线程结束。
		setIdThread.setDaemon(true);
		setIdThread.start();
		getIdThread.start();
		
		

	}

}
