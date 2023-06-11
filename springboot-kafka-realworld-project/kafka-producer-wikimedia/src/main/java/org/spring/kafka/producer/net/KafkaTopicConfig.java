package org.spring.kafka.producer.net;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	public NewTopic newTopic() {
		return TopicBuilder.name("wikimedia-recentchange").build();
	}
}
