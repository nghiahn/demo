package org.service2.config;

import org.service2.entity.MessageQueue;
import org.service2.service.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

@Configuration
public class RedisConfig {
	@Autowired
	private MessageQueue messageQueue;
	@Bean
	public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory, Receiver subscriber) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);
		Objects.requireNonNull(
				redisTemplate
						.getConnectionFactory())
						.getConnection()
						.subscribe(subscriber, messageQueue.getKey().getBytes());
		return redisTemplate;
	}
}
