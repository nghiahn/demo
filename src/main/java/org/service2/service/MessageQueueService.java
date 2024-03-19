package org.service2.service;

import org.service2.entity.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

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
}