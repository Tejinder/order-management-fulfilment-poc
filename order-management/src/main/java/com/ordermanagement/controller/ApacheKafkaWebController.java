package com.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.beans.Order;
import com.ordermanagement.service.KafkaSender;

@RestController
@RequestMapping(value = "/ordermanagement/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("noOfBooks") String noOfBooks, @RequestParam("bookName") String bookName, @RequestParam("user") String user, @RequestParam("orderType") String orderType) {
		String finalOrder = "noOfBooks=" + noOfBooks + "&bookName=" + bookName + "&user=" + user;
	//	kafkaSender.send(finalOrder);
		System.out.println("noOfBooks-->"+noOfBooks);
		System.out.println("bookName-->"+bookName);
		System.out.println("user-->"+user);
		
		Order order = new Order();
		order.setBookName(bookName);
		order.setNoOfBooks(Integer.valueOf(noOfBooks));
		order.setUser(user);
		order.setOrderType(orderType);
		
		kafkaSender.send(order);
		return "Message sent to the Kafka Topic order-topic Successfully";
	}

}