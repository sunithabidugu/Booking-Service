package com.obrs.entities;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor*/
//@JsonFormat 
public class Bus {
	private long busId;
	private String startLocation;
	private String endLocation;
	private  int capacity;
	private LocalDateTime arrivalTime;
	private LocalDateTime departureTime;
	
	
	
	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Bus(long busId, String startLocation, String endLocation, int capacity, LocalDateTime arrivalTime,
			LocalDateTime departureTime) {
		super();
		this.busId = busId;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.capacity = capacity;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}



	public long getBusId() {
		return busId;
	}



	public void setBusId(long busId) {
		this.busId = busId;
	}



	public String getStartLocation() {
		return startLocation;
	}



	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}



	public String getEndLocation() {
		return endLocation;
	}



	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}



	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public LocalDateTime getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}



	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", startLocation=" + startLocation + ", endLocation=" + endLocation
				+ ", capacity=" + capacity + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}
	
		
}
