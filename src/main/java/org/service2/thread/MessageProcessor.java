package org.service2.thread;

import jakarta.annotation.PostConstruct;
import org.service2.entity.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class MessageProcessor {
	@Autowired
	private MessageQueue messageQueue;
	private final ExecutorService executorService;

	public MessageProcessor() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);
	}

	@PostConstruct
	public void run() {
		System.out.println("Running...");
		while (true) {
			try {
				Object o  = messageQueue.take();
				Future<Object> future = executorService.submit(new Task(o.toString()));
				System.out.println(future.get());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
