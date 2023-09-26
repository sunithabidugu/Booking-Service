package com.obrs;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.obrs.controller.BookingController;
import com.obrs.entities.*;
import com.obrs.exception.BookingException;
import com.obrs.repository.BookingRepository;
import com.obrs.service.ServiceImpl;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)

public class ServiceImplTest {

    @InjectMocks
    private ServiceImpl bookingService;

    private MockMvc mockMvc;

    
    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestTemplate restTemplate;

    

       


        @Test
        public void testCreateBookingSuccess() throws BookingException {
            // Mock the behavior of bookingRepository
            when(bookingRepository.findById(anyInt())).thenReturn(Optional.empty());
            when(bookingRepository.save(any(Booking.class))).thenReturn(createSampleBooking());

            // Mock the behavior of restTemplate
            when(restTemplate.getForObject(anyString(), eq(Bus.class), anyLong())).thenReturn(createSampleBus());
            when(restTemplate.getForObject(anyString(), eq(Customer.class), anyLong())).thenReturn(createSampleCustomer());

            // Create a sample booking
            Booking booking = createSampleBooking();

            // Test the createBooking method
            ApiResponse response = bookingService.createBooking(booking);

            // Verify that the response contains the expected data
            assertNotNull(response);
            assertNotNull(response.getBooking());
            assertNotNull(response.getBus());
            assertNotNull(response.getCustomer());
            assertEquals(booking, response.getBooking());

            // Verify that the save method was called on bookingRepository
            verify(bookingRepository, times(1)).save(booking);
        }

        @Test
        public void testCreateBookingWithExistingId() {
            // Mock the behavior of bookingRepository to return a non-empty Optional
            when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(createSampleBooking()));

            // Create a sample booking
            Booking booking = createSampleBooking();

            // Test the createBooking method with an existing ID
            assertThrows(BookingException.class, () -> bookingService.createBooking(booking));

            // Verify that the save method was not called on bookingRepository
            verify(bookingRepository, never()).save(booking);
        }

        private Booking createSampleBooking() {
            Booking booking = new Booking();
            booking.setBookingId(1);
            booking.setBusId(101L);
            booking.setCustomerId(201L);
            // Set other booking properties as needed
            return booking;
        }

        private Bus createSampleBus() {
            Bus bus = new Bus();
            bus.setBusId(101L);
            // Set other bus properties as needed
            return bus;
        }

        private Customer createSampleCustomer() {
            Customer customer = new Customer();
            customer.setCustomerId(201L);
            // Set other customer properties as needed
            return customer;
        }
   

        
    @Test
    public void testDeleteByBookingIdNotFound() {
        // Mock the behavior of your repository
        when(bookingRepository.existsById(1)).thenReturn(false); // Booking with id 1 does not exist

        // Call the deleteByBookingId method and expect a BookingException
        assertThrows(BookingException.class, () -> bookingService.deleteBooking(1));

        // Verify that the repository's existsById method was called
        verify(bookingRepository, times(1)).existsById(1);
    }
    
    
    @Test
    public void testDeleteBooking() throws BookingException {
        // Arrange
        Booking booking = new Booking();
        booking.setBookingId(1);

        Mockito.when(bookingRepository.existsById(1)).thenReturn(true);
        Mockito.when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));

        // Act
        Booking deletedBooking = bookingService.deleteBooking(1);

        // Assert
        assertNotNull(deletedBooking);
        assertEquals(booking, deletedBooking);
    }

    

    

}

