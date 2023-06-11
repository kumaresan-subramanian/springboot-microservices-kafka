package org.kafka.consumer.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@KafkaListener(topics = "wikimedia-recentchange", groupId = "myGroup")
	public void consumeMessage(String message) {
		
		LOGGER.info(String.format("Event Message received : %s", message));
	}

}
