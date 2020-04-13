package com.orderfulfillment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderfulfillment.beans.Order;
import com.orderfulfillment.service.KafkaSender;

@RestController
@RequestMapping(value = "/ordermanagement/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("noOfBooks") String noOfBooks, @RequestParam("bookName") String bookName, @RequestParam("user") String user) {
		String finalOrder = "noOfBooks=" + noOfBooks + "&bookName=" + bookName + "&user=" + user;
		//kafkaSender.send(finalOrder);
		Order order = new Order();
		order.setBookName(bookName);
		order.setNoOfBooks(Integer.valueOf(noOfBooks));
		order.setUser(user);
		kafkaSender.send(order);
		return "Message sent to the Kafka Topic order-status-topic Successfully";
		//return "Order details sent to the Kafka Topic order-topic Successfully";
	}

}