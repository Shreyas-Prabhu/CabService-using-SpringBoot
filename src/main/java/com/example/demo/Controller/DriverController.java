package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.DriverService;

@RestController
public class DriverController {

	
	@Autowired
	DriverService dService;
	

	@PostMapping("/admin/addDriver") //The driver can be added by admin for only users who have registered to system.
	public String addDriver(@RequestBody Customer cus)
	{
			//System.out.println(cus.toString());
			return dService.addDriver(cus.getEmail());

	}
	
	@PostMapping("/admin/deleteDriver")  //Driver is removed and saved in the system as normal user, he/she is not driver anymore
	public String deleteDriver(@RequestBody Customer cus)
	{
	
			//System.out.println(cus.toString());
			return dService.deleteDriver(cus.getEmail());
		
	}
	

}
