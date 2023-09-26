package com.obrs.entities;

import java.util.Date;

public class Payment {
	
	private int paymentId;
	private Date paymentDate;
	private String paymentStatus;
	private int amount;
	private int customerId;
	private int bookingId;

	
	public Payment() {
		super();
	}

	
	public Payment(int paymentId, Date paymentDate, String paymentStatus, int amount, int customerId,
			int bookingId) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.amount = amount;
		this.customerId = customerId;
		this.bookingId = bookingId;
	}


	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	
}
