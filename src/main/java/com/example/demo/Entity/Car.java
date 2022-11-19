package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
	
	@Id
	private int car_id;
	private String car_name;
	private int car_capacity;
	private float car_rent;
	private int car_status;//For drivers to assign
	private int car_avail; //For users to book
	public int getCar_avail() {
		return car_avail;
	}
	public void setCar_avail(int car_avail) {
		this.car_avail = car_avail;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public int getCar_capacity() {
		return car_capacity;
	}
	public void setCar_capacity(int car_capacity) {
		this.car_capacity = car_capacity;
	}
	public float getCar_rent() {
		return car_rent;
	}
	public void setCar_rent(float car_rent) {
		this.car_rent = car_rent;
	}
	public int getCar_status() {
		return car_status;
	}
	public void setCar_status(int car_status) {
		this.car_status = car_status;
	}
	
	

}
