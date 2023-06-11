package com.orderservice.net.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basedomains.net.dto.Order;
import com.basedomains.net.dto.OrderEvent;

@Service
public class OrderProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
	
	private NewTopic topic;
	
	private KafkaTemplate<String, Order> kafkatemplate;
	
	public OrderProducer(NewTopic topic, KafkaTemplate<String, Order> kafkatemplate) {
		
		this.topic = topic;
		this.kafkatemplate = kafkatemplate;
	}
	
	public void sendMessage(OrderEvent event) {
		
		LOGGER.info(String.format("Message order event %s", event.toString()));
		
		Message<OrderEvent> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		this.kafkatemplate.send(message);
	}
}
