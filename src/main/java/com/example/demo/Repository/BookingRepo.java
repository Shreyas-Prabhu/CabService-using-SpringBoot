package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Booking;


public interface BookingRepo extends JpaRepository<Booking, Integer> {

	@Query("Select b from Booking b where b.cust_email = ?1 and b.isActive=1")
	public List<Booking> findBookingByEmail(String email);   //Find the booking of the customer which is currently active
	
	@Query("Select b from Booking b where b.car_id = ?1 and b.isActive=1")
	public Booking findActiveBookingByCarId(int car_id); //get the active booking details based on car id
}
