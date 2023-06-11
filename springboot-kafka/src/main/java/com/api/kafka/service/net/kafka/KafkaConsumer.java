package com.api.kafka.service.net.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.api.kafka.service.net.payload.User;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "myGroup")
	public void consumeMessage(String message) {
		
		LOGGER.info(String.format("Message received : %s", message));
	}
	
	@KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "myGroup")
	public void consumeMessage(User message) {
		
		LOGGER.info(String.format("Message received : %s", message.toString()));
	}
}
