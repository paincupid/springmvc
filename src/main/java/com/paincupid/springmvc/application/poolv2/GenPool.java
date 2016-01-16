package com.paincupid.springmvc.application.poolv2;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class GenPool {

	private Deque<Integer> deque;

	public GenPool(Deque<Integer> deque) {
		this.deque = deque;
	}

	public synchronized void set() {
		while (deque.size() == 30) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		deque.add(deque.getLast().intValue() + 1);
		System.out.printf("Set: %d\n", deque.getLast().intValue());
		notify();
	}

	public synchronized Integer get() {
		while (deque.size() == 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.printf("Get: %d: %s %s\n",deque.getFirst(), deque.size(),deque.poll());
		int result = deque.getFirst().intValue();
		System.out.println("------------Get: "+deque.getFirst()+", size: "+deque.size());
		deque.poll();
		notify();
		return result;
	}

}
