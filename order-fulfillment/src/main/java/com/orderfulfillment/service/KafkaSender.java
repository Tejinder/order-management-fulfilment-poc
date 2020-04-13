package com.orderfulfillment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.orderfulfillment.beans.Order;

@Service
public class KafkaSender {
	
	@Autowired
	//private KafkaTemplate<String, String> kafkaTemplate;
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	
	String kafkaTopic = "order-status-topic";
	
	/*public void send(String message) {
	    
	    kafkaTemplate.send(kafkaTopic, message);
	}*/
	
	public void send(Order order) {
	    
	    kafkaTemplate.send(kafkaTopic, order);
	}
}
