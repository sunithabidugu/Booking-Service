package com.obrs.entities;
import javax.persistence.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Builder
@Table
public class Booking {
	
	 @Id
	 @Min(value = 1, message = "Booking ID must be a positive integer")
	 private int bookingId;
	 
	 @NotBlank(message = "Select Time cannot be blank")
	 private String selectTime;
	 
	 @NotBlank(message = "Bus Type cannot be blank")
	 private String selectDate;
	 
	 @NotBlank(message = "Bus Type cannot be blank")
	 private String busType;
	 
	 @NotNull(message = "Bus ID cannot be null")
	 private Long busId;
	 
	 @NotNull(message = "Customer  ID cannot be null")
	 private long customerId;
	 
	
	 
	 
	 // private String bookingPersonName;
	 
//	 @Transient
//	 private List<Bus> bus = new ArrayList<>();
// 
//	 @Transient
//	 private List<Customer> customer = new ArrayList<>() ;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
	public Booking() {
		super();
	}


	public Booking(int bookingId, String selectTime, String selectDate, String busType, Long busId, long customerId) {
		super();
		this.bookingId = bookingId;
		this.selectTime = selectTime;
		this.selectDate = selectDate;
		this.busType = busType;
		this.busId = busId;
		this.customerId = customerId;
		
	}
	 

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", selectTime=" + selectTime + ", selectDate=" + selectDate
				+ ", busType=" + busType + ", busId=" + busId +  "]";
	}

	

	
	 
	 
	 
 

	 
	 
}
