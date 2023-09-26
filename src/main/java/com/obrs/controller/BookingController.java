package com.obrs.controller;


import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obrs.entities.ApiResponse;
import com.obrs.entities.Booking;
import com.obrs.repository.*;
import com.obrs.exception.BookingException;
import com.obrs.service.ServiceImpl;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {
	
 
	@Autowired
	 private ServiceImpl service;
	 
	private Logger logger=LoggerFactory.getLogger(BookingController.class);

		@PostMapping("/create")
		public ResponseEntity<ApiResponse> createBooking(@RequestBody Booking booking )throws BookingException{
			
			return new ResponseEntity<>(service.createBooking(booking),HttpStatus.OK);
		}
		@GetMapping("/all")
	    public List<Booking> getAllBookings() {
	        return service.getAllBookings();
	    }
		@GetMapping("/get/{bookingId}")
	    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") int bookingId) {
	        try {
	            Booking booking = service.getBookingById(bookingId);
	            return new ResponseEntity<>(booking, HttpStatus.OK);
	        } catch (BookingException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
		
		@PutMapping("/update/{bookingId}")
		public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody Booking updatedBooking) {
		    try {
		        Booking updated = service.updateBooking(bookingId, updatedBooking);
		        return ResponseEntity.ok(updated);
		    } catch (BookingException e) {
		        return ResponseEntity.notFound().build();
		    }
		}

		
		@DeleteMapping("/delete/{bookingId}")
		public ResponseEntity<Booking> deleteByBookingId(@PathVariable("bookingId") int bookingId)throws BookingException{
			return new ResponseEntity<>(service.deleteBooking(bookingId),HttpStatus.OK);
		}
		
		@GetMapping("/ticket/{customerId}")
		public ResponseEntity<List<ApiResponse>> downloadTicket(@PathVariable("customerId") int customerId)throws BookingException{
			return new ResponseEntity<>(service.downloadTicketWithBusCustDetails(customerId),HttpStatus.OK);

		}
		@GetMapping("/existingBooking")
		public ResponseEntity <List<ApiResponse>>  viewExistingBooking(){
			return new ResponseEntity<>(service.viewExistingBookingsWithBusDetails(),HttpStatus.OK);
		}
		
		@GetMapping("/viewBookingDetails/{bookingId}")
		public ResponseEntity <ApiResponse> viewBookingDetails(@PathVariable("bookingId") int bookingId)throws BookingException{
			return new ResponseEntity<>(service.viewBookingDetails(bookingId),HttpStatus.OK);
		}
}
