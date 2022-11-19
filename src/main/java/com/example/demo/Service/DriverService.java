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
		
	public String addDriver(String email)    //The driver can be added by admin for only users who have registered to system.
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
			if(cus.getRole().equalsIgnoreCase("admin"))
			{
				return "Do not assign driver to an admin, it is adviceable to register to the system using your personal email to be assigned"
						+ " as driver";
			}
			else {
			cus.setRole("driver");
			cus.setIsDriverAvailable(1);
			repo.save(cus);
			return "Driver added";
			}
		}
	}
	
	public String deleteDriver(String email) //Driver is removed and saved in the system as normal user, he/she is not driver anymore
	{
		Customer cus = repo.findByEmail(email);

		if(cus == null)
		{
			return "The driver with entered email is not present";
		}
		else if(cus.getRole().equalsIgnoreCase("driver") && cus.getIsDriverAvailable()==1)
		{
			cus.setRole("user");
			cus.setIsDriverAvailable(0);
			repo.save(cus);
			return "Driver deleted";
		}
		else
		{
			return "The driver is currently assigned to a car, unassign the car and then try to delete!!";
		}
	}
	

}
