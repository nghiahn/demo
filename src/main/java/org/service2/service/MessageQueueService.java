package org.service2.service;

import org.service2.entity.MessageQueue;
import org.service2.thread.MessageProcessor;
import org.service2.thread.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@EnableScheduling
public class MessageQueueService {
	@Autowired
	private MessageQueue messageQueue;
	@Autowired
	private StringRedisTemplate redisTemplate;

	public void sendMessage(String message) {
		redisTemplate.convertAndSend(messageQueue.getKey(), message);
	}

	public List<Object> messageQueueProcessor(ExecutorService executorService) {
		try {
			List<Future<Object>> futureList = new ArrayList<>();
			String message;
			while (!messageQueue.isEmpty()) {
				message = messageQueue.take().toString();
				futureList.add(executorService.submit(new Task(message)));
			}
			List<Object> resultList = new ArrayList<>();
			for (Future<Object> future : futureList) {
				resultList.add(future.get());
			}
			return resultList;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			System.err.println("Error while get result from Future : " + e.getMessage());
		}
		return null;
	}
}