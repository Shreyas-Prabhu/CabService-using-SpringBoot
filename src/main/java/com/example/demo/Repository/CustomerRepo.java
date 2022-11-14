package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
	
//	@Query("SELECT cus.email, cus.fname, cus.lname, cus.role FROM Customer cus WHERE cus.role = 'driver'")
//	public List<Object> getDrivers();
	
	public Customer findByEmail(String email);
	

}
