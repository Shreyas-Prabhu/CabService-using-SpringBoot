package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.BookingRepo;
import com.example.demo.Repository.CarRepo;
import com.example.demo.Repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo repo1;
	
	@Autowired
	CarRepo repo2;
	
	@Autowired
	BookingRepo repo3;
	
	public List<Car> getAvailSeatCar(int seat)
	{
		return repo2.getAllAvailableCarBasedOnSeat(seat);
	}
	
	public String bookCab(Booking book)
	{
		String email = book.getCust_email();
		int car_id=book.getCar_id();
		Customer cus = repo1.findByEmail(email);
		Car c = repo2.findById(car_id).orElse(null);
		
		if(c == null || cus == null)
		{
			return "There is a problem, try again";
		}
		
		else if(cus.getIsCustomerFree()==1 && c.getCar_avail()==1 && c.getCar_status()==0)
		{
			cus.setIsCustomerFree(0);
			c.setCar_avail(0);
			book.setIsActive(1);     
											//Could have added logic for UUID to create an unique transaction id for each booking, but to make it
											//easier, I have just added generated id for txn_id
			repo1.save(cus);
			repo2.save(c);
			repo3.save(book);
			return "Cab Booked";
		}
		else if(cus.getIsCustomerFree()==0)
		{
			return "You already have a booking";
		}
		else
		{
			return "car is not available at the moment";
		}
		
	}
	
	
	public String CancelCab(Booking book)
	{
		String email = book.getCust_email();
		int txn_id = book.getTxn_id();
		Booking book2=repo3.findById(txn_id).orElse(null);
		
		int car_id= book2.getCar_id();
		Car c = repo2.findById(car_id).orElse(null);
		Customer cus = repo1.findByEmail(email);
		if(book2 == null || c == null || cus == null)
		{
			return "There is a problem, try again with correct details";
		}
		else {
		cus.setIsCustomerFree(1);
		c.setCar_avail(1);
		book2.setIsActive(0);
		repo1.save(cus);
		repo2.save(c);
		repo3.save(book2);
		return "Cab Cancelled";
		}
	}
}
