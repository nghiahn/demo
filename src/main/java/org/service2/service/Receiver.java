package org.service2.service;

import org.service2.entity.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements MessageListener {
	@Autowired
	private MessageQueue messageQueue;
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String messageContent = new String(message.getBody());
		messageQueue.add(messageContent);
	}
}
