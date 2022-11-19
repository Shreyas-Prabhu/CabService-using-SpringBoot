package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.CarAssign;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.BookingRepo;
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
	
	@Autowired
	BookingRepo repo4;
	
	public String assignCar(CarAssign ca)    //Assign car to driver only if the user is driver and is registered with the system and 
											// driver is not assigned to any other car
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
			ca.setCar_name(c.getCar_name());
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
	
	
	public String unassignCar(String email)   //Unassign the current assigned car of driver
	{
		Customer cus = repo2.findByEmail(email);
		CarAssign ca = repo1.findByEmailActive(email);
		if(ca==null)
		{
			return "There is no car assigned to this driver";
		}
		int car_id = ca.getCar_id();
		Car c = repo3.findById(car_id).orElse(null);
		
		if(cus == null)
		{
			return "The entered email does not exist";
		}
		
		
		else if(repo4.findActiveBookingByCarId(car_id)==null) //unassign only if there is no booking for this car
		{
			cus.setIsDriverAvailable(1);
			c.setCar_status(1);
			ca.setIsActive(0);
			repo1.save(ca);
			repo2.save(cus);
			repo3.save(c);
			return "Driver with email :"+email+" unassigned from car with id:"+car_id;
		}
		else
		{
			
			return "The car cannot be unassigned because there is an active booking for this car";
		}
	}
		
	public List<CarAssign> getDriverCars(String email)  //Get all the cars used by the the entered driver email
	{
			
		return repo1.findDriverCarEmail(email);
			
			
			
	}
		
		
	
}
