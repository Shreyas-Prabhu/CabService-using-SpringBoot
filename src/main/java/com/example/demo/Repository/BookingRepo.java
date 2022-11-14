package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
