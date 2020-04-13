package com.orderfulfillment.service;

import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.orderfulfillment.beans.Order;

@Service
public class KafkaConsumer {
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@Autowired
	KafkaSender kafkaSender;
	
	String kafkaTopic = "order-topic";
	
	
	/*
	@KafkaListener(topics = "order-topic")
	  public void receive(String payload) {
	   System.out.println("received payload='{}'" +payload);
	   
	   sendOrderStatus("NEW");
	   try
	   {
		   Thread.sleep(5000);
	   }
	   catch(InterruptedException e)
	   {
		   
	   }
	   sendOrderStatus("SHIPPED");
	   
	   try
	   {
		   Thread.sleep(5000);
	   }
	   catch(InterruptedException e)
	   {
		   
	   }
	   sendOrderStatus("DELIVERED");
	    
	  }
	  */
	
	@KafkaListener(topics = "order-topic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
     
        System.out.println("received payload='{}'" +cr.toString());
        System.out.println("received payload='{}' VALUE" +cr.value());
        
        Order order = (Order)cr.value();
        System.out.println("bookName-->"+ order.getBookName());
        
        
       Order sendOrder = new Order();
       
       sendOrder.setOrderId(UUID.randomUUID()+"");
       sendOrder.setStatus("NEW");
       sendOrder.setBookName(order.getBookName());
       sendOrder.setUser(order.getUser());
       sendOrder.setNoOfBooks(order.getNoOfBooks());
       sendOrder.setOrderType(order.getOrderType());
       
        sendOrderStatus(sendOrder);
 	   try
 	   {
 		   Thread.sleep(5000);
 	   }
 	   catch(InterruptedException e)
 	   {
 		   
 	   }
 	  sendOrder.setStatus("SHIPPED");
 	   sendOrderStatus(sendOrder);
 	   
 	   try
 	   {
 		   Thread.sleep(5000);
 	   }
 	   catch(InterruptedException e)
 	   {
 		   
 	   }
 	  sendOrder.setStatus("DELIVERED");
 	   sendOrderStatus(sendOrder);
    }
	
	/*public void sendOrderStatus(String orderStatus)
	{
		kafkaSender.send(orderStatus);
	}*/
	
	public void sendOrderStatus(Order orderStatus)
	{
		kafkaSender.send(orderStatus);
	}
	
}
