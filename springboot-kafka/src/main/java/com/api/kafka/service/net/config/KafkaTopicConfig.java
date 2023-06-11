package com.api.kafka.service.net.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

//	public NewTopic createTopic() {
//		return TopicBuilder.name("springbootkafka").build();
//	}
	
	public NewTopic createJsonTopic() {
		return TopicBuilder.name("springbootkafkaJson").build();
	}
}
