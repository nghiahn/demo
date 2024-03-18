package org.service2.thread;

import java.util.concurrent.Callable;

public class Task implements Callable<Object> {
	private final Object message;
	public Task(Object message) {
		this.message = message;
	}

	@Override
	public Object call() {
		String name = Thread.currentThread().getName();
		try {
			Thread.sleep(2000);
			if (message != null) {
				return name + " result is " + message;
			}
		} catch (InterruptedException e) {
			System.err.println("Error in take message from queue! " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		return null;
	}
}
