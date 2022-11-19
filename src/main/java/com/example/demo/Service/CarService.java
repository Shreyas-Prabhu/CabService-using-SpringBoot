package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Entity.Car;

import com.example.demo.Repository.CarRepo;

@Service
public class CarService {   //All actions can only be taken by admin
	
	@Autowired
	CarRepo repo;
	
	public List<Car> getAllCars()    //Get all the cars present in the system
	{
		return repo.findAll();
	}
	
	public List<Car> getAllAvailCar()   //Get all cars available which are not assigned with any driver
	{
		return repo.getAllAvailableCar();
	}
	
	public Optional<Car> getCar(int id)  //Get a car with car id
	{
		return repo.findById(id);
	}
	
	public String saveCar(Car car)  //Save a car with unique id
	{
		if(!repo.existsById(car.getCar_id()))
		{	
			car.setCar_avail(1);
			car.setCar_status(1);
			repo.save(car);
			return "Saved";
		}
		else
		{
			return "The id : "+car.getCar_id()+" is already present.";
		}
	}
	
	public String updateCar(Car car)  //Update the car present in the system
	{
		if(repo.existsById(car.getCar_id()))
		{
			repo.save(car);
			return "Updated";
		}
		else
		{
			return "The id : "+car.getCar_id()+" is not present.";
		}
	}
	
	public String deleteCar(int id)  //Delete the car
	{
		if(repo.existsById(id))
		{
			Car c = repo.findById(id).orElse(null);
			if(c.getCar_status()==0)
			{
				return "Can not delete this car as it is already assigned!!";
			}
			
			else 
			{
				repo.deleteById(id);;
				return "Deleted";
			}
		}
		else
		{
			return "The id : "+id+" is not present.";
		}
	}
	
	
	
}
