package com.ordermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ordermanagement.beans.Order;

@Service
public class KafkaSender {
	
	@Autowired
	//private KafkaTemplate<String, String> kafkaTemplate;
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	String kafkaTopic = "order-topic";
	
	/*public void send(String message) {
	    
	    kafkaTemplate.send(kafkaTopic, message);
	    
	}
	*/
	public void send(Order order) {
	    
		/*Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();
	   // kafkaTemplate.send(kafkaTopic, message);
	   */
		kafkaTemplate.send(kafkaTopic, order);
		// kafkaTemplate.send(message);
	    
	}
	
}
