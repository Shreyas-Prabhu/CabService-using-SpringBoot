package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.CarAssign;

@Repository
public interface CarAssignRepo extends JpaRepository<CarAssign, Integer> {

	@Query("Select ca from CarAssign ca where ca.driver_email= ?1 and ca.isActive=1")
	public CarAssign findByEmailActive(String email);  //Get the current assigned car id
	
	@Query("Select ca from CarAssign ca where ca.driver_email = ?1")
	public List<CarAssign> findDriverCarEmail(String email);  //Get all the cars used by the the entered driver email
}
