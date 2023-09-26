package com.obrs.entities;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ApiResponse {
	private  Booking booking;
	private Bus bus;
	private List<Payment> payment ;
	private Customer customer;
	
	
	

	public ApiResponse() {
		super();
	}




	public ApiResponse(Booking booking, Bus bus, List<Payment> payment, Customer customer) {
		super();
		this.booking = booking;
		this.bus = bus;
		this.payment = payment;
		this.customer = customer;
	}




	public Booking getBooking() {
		return booking;
	}




	public void setBooking(Booking booking) {
		this.booking = booking;
	}




	public Bus getBus() {
		return bus;
	}




	public void setBus(Bus bus) {
		this.bus = bus;
	}




	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	

	
	
	
}
