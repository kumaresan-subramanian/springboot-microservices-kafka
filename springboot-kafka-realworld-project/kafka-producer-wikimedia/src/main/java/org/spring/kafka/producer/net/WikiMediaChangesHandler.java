package org.spring.kafka.producer.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;

public class WikiMediaChangesHandler implements BackgroundEventHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesHandler.class);
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topic;
	
	public WikiMediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic){
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	} 
	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info(String.format("Event data : %s", messageEvent.getData()));
		this.kafkaTemplate.send(topic, messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
