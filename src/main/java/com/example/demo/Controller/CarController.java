package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Car;
import com.example.demo.Service.CarService;


@RestController
public class CarController {

	@Autowired
	CarService cService;
		
	

	@GetMapping("/admin/getAvailableCars")
	public List<Car> fetchAvailable()
	{
		return cService.getAllAvailCar();
	
	}
	
	
	@GetMapping("/admin/getAllCars")
	public List<Car> fetchAll()
	{
		return cService.getAllCars();
	}
	
	@PostMapping("/admin/saveCar")
	public String saveData(@RequestBody Car car)
	{
		return cService.saveCar(car);

	}
	
	
	@DeleteMapping("/admin/deleteCar/{car_id}")
	public String deleteCar(@PathVariable("car_id") int id)
	{

		return cService.deleteCar(id);
		
	}
}
