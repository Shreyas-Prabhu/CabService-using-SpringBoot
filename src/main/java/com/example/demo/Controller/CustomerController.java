package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.CarAssign;
import com.example.demo.Service.CustomerService;


@RestController
public class CustomerController {

	
	@Autowired
	CustomerService cusService;
	
	@GetMapping("/user/getAvailCar")  //Search the car based on seat in params
	public List<Car> getAvailCarSeat(@RequestParam int seat)
	{
		return cusService.getAvailSeatCar(seat);
	}
	
	@PostMapping("/user/bookCab")   // Book the car by user,driver and admin
	public String cabBook(@RequestBody Booking book, Principal p)
	{
		return cusService.bookCab(book, p.getName());
	}
	
	@PostMapping("/user/cancelCab")  //Cancel the cab by providing the transaction id of booking
	public String cabCancel(@RequestBody Booking book,Principal p)
	{
		return cusService.CancelCab(book, p.getName());
	}
	
	@GetMapping("/user/allBookings")   //The active booking of the customer can be found out where transaction id is mentioned
	public List<Booking> showAll(Principal p)
	{
		return cusService.showAllActiveBooking(p.getName());
	}
	
	@PostMapping("/driver/getListCar")   // driver can get the list of all car ids, and the status whther it is current car or previous car
	public List<CarAssign> getDriverCars(Principal p)
	{
		return cusService.getCarList(p.getName());
	}
	

}
