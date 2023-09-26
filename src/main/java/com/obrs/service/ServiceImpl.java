package com.obrs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.obrs.entities.ApiResponse;
//import com.obrs.entities.ApiResponse;
import com.obrs.entities.Booking;
import com.obrs.entities.Bus;
import com.obrs.entities.Customer;
import com.obrs.entities.Payment;
import com.obrs.exception.BookingException;
import com.obrs.exception.CustomerException;

import com.obrs.repository.BookingRepository;

@Service

public class ServiceImpl implements IService {

		@Autowired
		private BookingRepository bookingRepository;
		
		@Autowired
		private RestTemplate restTemplate;
		
		private Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
				
		
	@Override
	public ApiResponse createBooking(Booking booking)throws BookingException {
		//Booking booking= bookingRepository.findById( )
				Optional<Booking> a = bookingRepository.findById(booking.getBookingId());
				Long id = booking.getBusId();
				long customerId = booking.getCustomerId();
				
			if(a.isPresent()) {
			throw new BookingException("Id already exist");	
		}
			else {
				try {
					
					Bus bus = restTemplate.getForObject("http://localhost:8082/api/getBusById/{id}", Bus.class,id);
					logger.info("Fetched Bus Details: {}", bus);
					
                	ApiResponse response = new ApiResponse();

					Customer customer = restTemplate.getForObject("http://localhost:8085/customer/getcustomer/{customerId}", Customer.class,customerId);
					logger.info("Fetched Customer Details: {}",customer);

					if (bus != null) {
                	response.setBus(bus);
                	response.setBooking(booking);
                	response.setCustomer(customer);
                    
                } 
					else {
                    throw new BookingException("Bus details not found for busId: " + id);
                }
					if(customer!=null) {
						response.setCustomer(customer);
					}
					else {
						throw new BookingException("Customer details not found");
					}
	
                    Booking createdBooking =    bookingRepository.save(booking);
                    return response;
					
				} catch (Exception e) {
                throw new BookingException("Error fetching bus details: " + e.getMessage());
            }
        }
    }



	@Override
	public Booking deleteBooking(int id) {
		
		if(bookingRepository.existsById(id))
		{
		 Booking booking =  bookingRepository.findById(id).get();
		 bookingRepository.deleteById(id);
		 return booking; 
		 
		}
		else {
			throw new BookingException("Not able to delete Booking");
		}
	}
	
	
	
	@Override
	public List<ApiResponse> downloadTicketWithBusCustDetails(long CustomerId) throws BookingException {
	    List<ApiResponse> responses = new ArrayList<>();
	    
	    // Fetch multiple bookings by CustomerId
	    List<Booking> bookings = bookingRepository.findByIdlong(CustomerId);

	    if (bookings.isEmpty()) {
	        throw new BookingException("No bookings found for CustomerId: " + CustomerId);
	    }
	    
	    for (Booking booking : bookings) {
	        Long busId = booking.getBusId();
	        Long customerId = booking.getCustomerId();
	        

	        // Make an HTTP GET request to fetch bus details by busId
	        Bus bus = restTemplate.getForObject("http://localhost:8082/api/getBusById/{id}", Bus.class, busId);

	        Customer customer = restTemplate.getForObject("http://localhost:8085/customer/getcustomer/{customerId}", Customer.class, customerId);

	        ApiResponse response = new ApiResponse();

	        if (bus != null) {
	            response.setBooking(booking);
	            response.setBus(bus);
	        } else {
	            throw new BookingException("Bus details not found for busId: " + busId);
	        }

	        if (customer != null) {
	            response.setCustomer(customer);
	            responses.add(response);
	        } else {
	            throw new BookingException("No customer details found");
	        }
	    }
	    
	    return responses;
	}

	


	   
	 @Override
	    public List<ApiResponse> viewExistingBookingsWithBusDetails() {
	        List<Booking> existingBookings = bookingRepository.findAll();
	        List<ApiResponse> responses = new ArrayList<>();

	        for (Booking booking : existingBookings) {
	            Long busId = booking.getBusId();
	            int bookingId = booking.getBookingId();
	            Long customerId = booking.getCustomerId();

	            // Make an HTTP GET request to fetch bus details by busId
	            Bus bus = restTemplate.getForObject("http://localhost:8082/api/getBusById/{id}", Bus.class, busId);
		        Customer customer = restTemplate.getForObject("http://localhost:8085/customer/getcustomer/{customerId}", Customer.class, customerId);

	            ResponseEntity<List<Payment>> paymentResponse = restTemplate.exchange(
		    		    "http://localhost:8081/payments/getpaymentbybookingid/{bookingId}",
		    		    HttpMethod.GET,
		    		    null,
		    		    new ParameterizedTypeReference<List<Payment>>() {}, // Specify the expected response type
		    		    bookingId
		    		);
		         			List<Payment> paymentsArray = paymentResponse.getBody();
		     				logger.info("Fetched Payment Details: {}",paymentResponse);

			                ApiResponse response = new ApiResponse();

	            if (bus != null) {
	                response.setBooking(booking);
	                response.setBus(bus);
	                if(paymentResponse!=null) {
	                	response.setPayment(paymentsArray);
	                }
	                responses.add(response);


	            }else {
              throw new BookingException("Bus details not found for busId: " + busId);
	            }
	            if(customer!= null) {
	            	response.setCustomer(customer);
	            }
	            else {
	            	throw new BookingException("Customer Details not found");
	            }
	        }

	        return responses;
	    }


	 
	 @Override
	 public ApiResponse viewBookingDetails(int bookingId) throws BookingException {
	     Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

	     if (!bookingOptional.isPresent()) {
	         throw new BookingException("Booking with Id not found");
	     }

	     Booking booking = bookingOptional.get();
	     Long busId = booking.getBusId();
	     Long customerId = booking.getCustomerId();


	     // Make an HTTP GET request to fetch bus details by busId
	     Bus bus = restTemplate.getForObject("http://localhost:8082/api/getBusById/{id}", Bus.class, busId);
	     logger.info("Fetched Bus Details: {}", bus);
	     
	     Customer customer = restTemplate.getForObject("http://localhost:8085/customer/getcustomer/{customerId}", Customer.class, customerId);
         ApiResponse response = new ApiResponse();


	     // Make an HTTP GET request to fetch payments by bookingId from Payment service
	     ResponseEntity<List<Payment>> paymentResponse = restTemplate.exchange(
	    		    "http://localhost:8081/payments/getpaymentbybookingid/{bookingId}",
	    		    HttpMethod.GET,
	    		    null,
	    		    new ParameterizedTypeReference<List<Payment>>() {}, // Specify the expected response type
	    		    bookingId
	    		);
	         			List<Payment> paymentsArray = paymentResponse.getBody();
	     				logger.info("Fetched Payment Details: {}",paymentResponse);

	     if(paymentResponse!=null) {
	    	 
	     }
	     if (bus != null) {
	         response.setBooking(booking);
	         response.setBus(bus);

	         // Extract payments from the response entity
	         response.setPayment(paymentsArray); // Assuming ApiResponse has a setPayments method
	         List<Payment>  list= response.getPayment();
	         System.out.println(list);

	     } else {
	         throw new BookingException("Bus details not found for busId: " + busId);
	     }
	     if(customer!= null) {
	    	 response.setCustomer(customer);
	         return response;

	     }
	     else {
	    	 throw new CustomerException("Customer not found");
	     }
	 }



	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}



	@Override
	public Booking getBookingById(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            throw new BookingException("Booking not found with ID: " + bookingId);
        }
	}



	@Override
	public Booking updateBooking(int bookingId, Booking updatedBooking) throws BookingException {
		if (bookingRepository.existsById(bookingId)) {
            Booking existingBooking = bookingRepository.findById(bookingId).get();

            existingBooking.setSelectTime(updatedBooking.getSelectTime());
            existingBooking.setSelectDate(updatedBooking.getSelectDate());
            existingBooking.setBusType(updatedBooking.getBusType());
            existingBooking.setBusId(updatedBooking.getBusId());
            existingBooking.setCustomerId(updatedBooking.getCustomerId());

            return bookingRepository.save(existingBooking);
        } else {
            throw new BookingException("Booking not found with ID: " + bookingId);
        }
	}


}
