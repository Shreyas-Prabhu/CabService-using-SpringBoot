package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Entity.Car;

import com.example.demo.Repository.CarRepo;

@Service
public class CarService {
	
	@Autowired
	CarRepo repo;
	
	public List<Car> getAllCars()
	{
		return repo.findAll();
	}
	
	public List<Car> getAllAvailCar()
	{
		return repo.getAllAvailableCar();
	}
	
	public Optional<Car> getCar(int id)
	{
		return repo.findById(id);
	}
	
	public String saveCar(Car car)
	{
		if(!repo.existsById(car.getCar_id()))
		{
			repo.save(car);
			return "Saved";
		}
		else
		{
			return "The id : "+car.getCar_id()+" is already present.";
		}
	}
	
	public String updateCar(Car car)
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
	
	public String deleteCar(int id)
	{
		if(repo.existsById(id))
		{
			Car c = repo.findById(id).orElse(null);
			if(c.getCar_status()==0)
			{
				return "Can not delete this car as it is already booked!!";
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
