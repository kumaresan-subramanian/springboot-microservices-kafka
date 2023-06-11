package com.orderservice.net.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basedomains.net.dto.Order;
import com.basedomains.net.dto.OrderEvent;
import com.orderservice.net.kafka.OrderProducer;

@RestController
@RequestMapping("api/order")
public class OrderController {

	OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}
	
	@PostMapping("publish")
	public String palceOrder(@RequestBody Order order) {
		
		order.setOrderId(Uuid.randomUuid().toString());
		
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setOrder(order);
		orderEvent.setMessage("order status in pending state");
		orderEvent.setStatus("Pending");
		
		orderProducer.sendMessage(orderEvent);
		return "Order palced successfully";
		
	}
	
}
