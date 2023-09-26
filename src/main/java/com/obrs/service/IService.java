package com.obrs.service;

import java.util.List;

import com.obrs.entities.ApiResponse;
//import com.obrs.entities.ApiResponse;
import com.obrs.entities.Booking;
import com.obrs.exception.BookingException;

public interface IService {
	
	public ApiResponse createBooking(Booking booking)throws BookingException;
	public Booking deleteBooking(int id)throws BookingException;
	//public List<ApiResponse> downloadTicketWithBusCustDetails(long customerId)throws BookingException;
	public List<ApiResponse> viewExistingBookingsWithBusDetails();
	public ApiResponse viewBookingDetails(int bookingId);
//	public ApiResponse getBookingById(int bookingId);
	List<ApiResponse> downloadTicketWithBusCustDetails(long CustomerId) throws BookingException;
	public List<Booking> getAllBookings();
	public Booking getBookingById(int id);
	 Booking updateBooking(int bookingId, Booking updatedBooking) throws BookingException;
	

}
