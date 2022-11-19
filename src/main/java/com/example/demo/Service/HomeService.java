package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepo;

@Service
public class HomeService  {

	@Autowired
	CustomerRepo repo;
	
	@Autowired 
	private BCryptPasswordEncoder bcryptEncoder;
	
	public String Register(Customer cus)
	{
		String email = cus.getEmail();
		String pass = cus.getPassword();
		Customer cus2 = repo.findByEmail(email);
		
		if(cus2 == null)
		{
			
			cus.setPassword(bcryptEncoder.encode(pass));
			cus.setRole("user");
			cus.setIsDriverAvailable(0);
			cus.setIsCustomerFree(1);
			repo.save(cus);
			return "Registered Successfully. Please Login!!";
		}
		else
		{
			return "You already have an account";
		}
	}

	
	

}
