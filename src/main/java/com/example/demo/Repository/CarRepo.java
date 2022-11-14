package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

	@Query("SELECT c FROM Car c WHERE c.car_status = 1")
	public List<Car> getAllAvailableCar();
	
	@Query("SELECT c FROM Car c WHERE c.car_status = 0 and c.car_avail = 1 and c.car_capacity= ?1")
	public List<Car> getAllAvailableCarBasedOnSeat(int seat);//get car with required seat only if the car is available to book and is assigned a driver
}
