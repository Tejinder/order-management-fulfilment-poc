package com.orderfulfillment.beans;

public class Order {

	private String bookName;
	private Integer noOfBooks;
	private String user;
	private String status;
	private String orderId;
	private String orderType;
	
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(Integer noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
