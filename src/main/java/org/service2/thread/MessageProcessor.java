package org.service2.thread;

import org.service2.entity.MessageQueue;
import org.service2.service.MessageQueueService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class MessageProcessor implements ApplicationContextAware {
	@Autowired
	private MessageQueue messageQueue;
	@Autowired
	private MessageQueueService messageQueueService;
	private final ExecutorService executorService;
	private final ScheduledExecutorService scheduledExecutorService;

	public MessageProcessor() {
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);
	}

	public void run() {
		while (!messageQueue.isEmpty()) {
			System.out.println("Running...");
			List<Object> objectList = messageQueueService.messageQueueProcessor(executorService);
			for (Object obj : objectList) {
				System.out.println(obj);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		scheduledExecutorService.scheduleAtFixedRate(this::run, 0, 1000, TimeUnit.MILLISECONDS);
	}
}
