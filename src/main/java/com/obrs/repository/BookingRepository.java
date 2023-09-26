package com.obrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.obrs.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository <Booking,Integer> {

    @Query(value = "select * from booking WHERE customer_id = ?1", nativeQuery = true)
	List<Booking> findByIdlong(long customerId);
    


	
}
