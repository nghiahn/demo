package org.service2.entity;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
@Scope("singleton")
public class MessageQueue {

	private static final String QUEUE_KEY = "messageQueue";

	private final Queue<Object> queue = new ConcurrentLinkedDeque<>();

	public String getKey() {
		return QUEUE_KEY;
	}

	public Integer size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public void add(Object object) {
		queue.add(object);
	}

	public Object take() {
		return queue.poll();
	}

	@PostConstruct
	public void initMockData() {
		queue.addAll(List.of("a", "b", "c", "d", "e", "r", "r", "r", "f", "f"));
	}
}
