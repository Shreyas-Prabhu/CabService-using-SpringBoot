package com.example.demo.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customer;

import com.example.demo.Repository.CustomerRepo;



@Service
public class DriverService {
	
	@Autowired
	CustomerRepo repo;
		
	public String addDriver(String email)
	{
		Customer cus = repo.findByEmail(email);

		if(cus == null)
		{
			return "The person should be registered to app";
		}
		
		else if(cus.getRole().equalsIgnoreCase("driver"))
		{
			return "The person is already a driver";
		}
		else
		{
			cus.setRole("driver");
			cus.setIsDriverAvailable(1);
			repo.save(cus);
			return "Driver added";
		}
	}
	
	public String deleteDriver(String email)
	{
		Customer cus = repo.findByEmail(email);

		if(cus == null)
		{
			return "The driver with entered email is not present";
		}
		else if(cus.getRole().equalsIgnoreCase("driver"))
		{
			cus.setRole("user");
			cus.setIsDriverAvailable(0);
			repo.save(cus);
			return "Driver deleted";
		}
		else
		{
			return "The driver with entered email is not present";
		}
	}
	
//	public List<Object> getAllDrivers()
//	{
//		return repo.getDrivers();
//	}
}
