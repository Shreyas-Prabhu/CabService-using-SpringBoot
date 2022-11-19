package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.CarAssign;
import com.example.demo.Service.CarAssignService;

@RestController
public class CarAssignController {
	
	@Autowired
	CarAssignService caService;
	
	@PostMapping("/admin/carAssign")       //Assign car to driver only if the user is driver and is registered with the system and 
										   // driver is not assigned to any other car
	public String carAssigntoDriver(@RequestBody CarAssign ca)
	{
		return caService.assignCar(ca);
	}
	
	@PostMapping("/admin/carUnassign")   //Unassign the current assigned car of driver
	public String carUnassign(@RequestBody CarAssign ca)
	{
		return caService.unassignCar(ca.getDriver_email());
	}
	
	@PostMapping("/admin/getDriverCars")     //Get all the cars used by the the entered driver email
	public List<CarAssign> getDriverCars(@RequestBody CarAssign ca)
	{
		return caService.getDriverCars(ca.getDriver_email());
	}
}
