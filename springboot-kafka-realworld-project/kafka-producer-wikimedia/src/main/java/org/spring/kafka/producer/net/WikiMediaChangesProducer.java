package org.spring.kafka.producer.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

@Service
public class WikiMediaChangesProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate){
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage() throws URISyntaxException, InterruptedException {
		
		String topic = "wikimedia-recentchange";
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		
		BackgroundEventHandler evnethandler = new WikiMediaChangesHandler(kafkaTemplate, topic);
		EventSource.Builder eventSource = new EventSource.Builder(new URI(url));
		BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(evnethandler, eventSource);
		BackgroundEventSource backgroundEventSource = builder.build();
		backgroundEventSource.start();
		TimeUnit.SECONDS.sleep(1);
	}
	
}
