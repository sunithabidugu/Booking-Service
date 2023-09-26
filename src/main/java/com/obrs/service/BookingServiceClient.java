package com.obrs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.obrs.entities.Booking;
@FeignClient(name = "booking-service") // Replace "booking-service" with the actual service name

public interface BookingServiceClient {


	    @PostMapping("/booking/create")
	    ResponseEntity<Booking> createBooking(@RequestBody Booking booking);

	    @DeleteMapping("/booking/delete/{bookingId}")
	    ResponseEntity<Booking> deleteBooking(@PathVariable("bookingId") int bookingId);

	    @GetMapping("/booking/ticket/{customerId}")
	    ResponseEntity<Booking> downloadTicket(@PathVariable("customerId") int customerId);

	    @GetMapping("/booking/existingBooking")
	    ResponseEntity<List<Booking>> viewExistingBooking();

	    @GetMapping("/booking/viewBookingDetails/{bookingId}")
	    ResponseEntity<Booking> viewBookingDetails(@PathVariable("bookingId") int bookingId);
	

}
