package com.api.kafka.service.net.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.api.kafka.service.net.payload.User;

@Service
public class KafkaJsonProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkatemplate;
	
	@Value("${spring.kafka.topic-json.name}")
	private String jsonTopic;
	
	public void sendMessage(User message) {
		
		LOGGER.info(String.format("Message Send %s", message.toString()));
		Message<User> msg = MessageBuilder.withPayload(message)
				.setHeader(KafkaHeaders.TOPIC, this.jsonTopic)
				.build();
		kafkatemplate.send(msg);
	}
}
