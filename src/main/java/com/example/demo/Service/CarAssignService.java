package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.CarAssign;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CarAssignRepo;
import com.example.demo.Repository.CarRepo;
import com.example.demo.Repository.CustomerRepo;

@Service
public class CarAssignService {

	@Autowired
	CarAssignRepo repo1;
	
	@Autowired
	CustomerRepo repo2;
	
	@Autowired
	CarRepo repo3;
	
	public String assignCar(CarAssign ca)
	{
		String email = ca.getDriver_email();
		int car_id=ca.getCar_id();
		
		Customer cus = repo2.findByEmail(email);
		Car c = repo3.findById(car_id).orElse(null);
		if( cus == null )
		{
			return "Not a driver and not registered member";
		}
		
		else if(c==null)
		{
			return "There is no car with id: " +car_id;
		}
		else if(cus.getIsDriverAvailable()==1 && c.getCar_status()==1 && cus.getRole().equalsIgnoreCase("driver"))
		{
			cus.setIsDriverAvailable(0);
			c.setCar_status(0);
			ca.setIsActive(1);
			repo2.save(cus);
			repo3.save(c);
			repo1.save(ca);
			return "car with id : "+car_id+" assigned to "+email;
			
		}
		else
		{
			if(cus.getIsDriverAvailable()==0)
				return "This driver is not available";
			else
				return "This car is not available";
		}
				
	}
	
	
	public String unassignCar(String email)
	{
		Customer cus = repo2.findByEmail(email);
		CarAssign ca = repo1.findByEmailActive(email);
		
		int car_id = ca.getCar_id();
		Car c = repo3.findById(car_id).orElse(null);
		
		if(cus == null)
		{
			return "The entered email does not exist";
		}
		
		else
		{
			cus.setIsDriverAvailable(1);
			c.setCar_status(1);
			ca.setIsActive(0);
			repo1.save(ca);
			repo2.save(cus);
			repo3.save(c);
			return "Driver with email :"+email+" unassigned from car with id:"+car_id;
		}
	}
		
	public List<CarAssign> getDriverCars(String email)
	{
			
		return repo1.findDriverCarEmail(email);
			
			
			
	}
		
		//Car c = repo3.findById(car_id).orElse(null);
	
}
