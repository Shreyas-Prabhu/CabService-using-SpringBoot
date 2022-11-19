package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.CarAssign;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.BookingRepo;
import com.example.demo.Repository.CarAssignRepo;
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
	
	@Autowired
	CarAssignRepo repo4;
	
	public List<Car> getAvailSeatCar(int seat)    //Search the car based on seat in params
	{
		return repo2.getAllAvailableCarBasedOnSeat(seat);
	}
	
	public String bookCab(Booking book, String email)  // Book the car by user,driver and admin
	{
		//String email = book.getCust_email();
		int car_id=book.getCar_id();
		Customer cus = repo1.findByEmail(email);
		Car c = repo2.findById(car_id).orElse(null);
		
		if(c == null || cus == null)
		{
			return "There is a problem, try again";
		}
		
		if(cus.getRole().equalsIgnoreCase("driver")	&& cus.getIsDriverAvailable()==0)
		{
			return "You can not book the cab as you are driver having assigned a car, please get the car unassigned by the admin"
					+ " to book a cab";
		}
				
				
				
		else if(cus.getIsCustomerFree()==1 && c.getCar_avail()==1 && c.getCar_status()==0)
		{
			cus.setIsCustomerFree(0);
			c.setCar_avail(0);
			book.setCust_email(email);
			book.setIsActive(1);     
											//Could have added logic for UUID to create an unique transaction id for each booking, but to make it
											//easier, I have just added generated id for txn_id
			repo1.save(cus);
			repo2.save(c);
			repo3.save(book);
			return "Cab Booked with transaction id: "+book.getTxn_id();
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
	
	
	public String CancelCab(Booking book, String email)  //Cancel the cab by providing the transaction id of booking
	{
		
		int txn_id = book.getTxn_id();
		Booking book2=repo3.findById(txn_id).orElse(null);
		if(book2 == null)
		{
			return "Some error with the transaction id, enter correctly";
		}
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

	public List<Booking> showAllActiveBooking(String email) {  //The active booking of the customer can be found out where transaction id is mentioned
		return repo3.findBookingByEmail(email);
	}

	public List<CarAssign> getCarList(String email) {// driver can get the list of all car ids, and the status whther it is current car or previous car
		
		return repo4.findDriverCarEmail(email);
	}
}
