package com.api.kafka.service.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.kafka.service.net.kafka.KafkaJsonProducer;
import com.api.kafka.service.net.kafka.KafkaProducer;
import com.api.kafka.service.net.payload.User;

@RestController
@RequestMapping("api/kafka")
public class KafkaController {

//	@Autowired
//	private KafkaProducer kafkaProducer;
	
	@Autowired
	private KafkaJsonProducer kafkaJsonProducer;
	
	
//	@GetMapping("publish")
//	public ResponseEntity<String> publishMessage(@RequestParam String message){
//		
//		kafkaProducer.sendMessage(message);
//		return ResponseEntity.ok("Message send to the topic");
//	}
	
	@PostMapping("/jsonPublish")
	public ResponseEntity<String> publishMessage(@RequestBody User message){
		
		kafkaJsonProducer.sendMessage(message);
		return ResponseEntity.ok("Message send to the topic");
	}
}
