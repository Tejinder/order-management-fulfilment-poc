package com.ordermanagement.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ordermanagement.beans.Order;



@Service
public class KafkaConsumer {
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@Autowired
	KafkaSender kafkaSender;
	
	String kafkaTopic = "order-status-topic";
	
	
	
	@KafkaListener(topics = "order-status-topic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
     
        System.out.println("received payload='{}'" +cr.toString());
        System.out.println("received payload='{}' VALUE" +cr.value());
        
        Order order = (Order)cr.value();
        System.out.println("bookName-->"+ order.getBookName());
        System.out.println("NoOfBooks-->"+ order.getNoOfBooks());
        System.out.println("status-->"+ order.getStatus());
        System.out.println("user-->"+ order.getUser());
        System.out.println("orderId-->"+ order.getOrderId());
        System.out.println("orderType-->"+ order.getOrderType());
        
       
    }
	
	
	
}
