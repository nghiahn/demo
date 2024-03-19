package org.service2.entity;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MessageQueue {

	private static final String QUEUE_KEY = "messageQueue";

	private final BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

	public String getKey() {
		return QUEUE_KEY;
	}

	public Integer size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return !queue.isEmpty();
	}

	public void add(Object object) {
		queue.add(object);
	}

	public Object take() throws InterruptedException {
		return queue.take();
	}

	public Object poll() {
		return queue.poll();
	}

	@PostConstruct
	public void initMockData() {
		queue.addAll(List.of("a", "b", "c", "d", "e", "r", "r", "r", "f", "f"));
	}
}
