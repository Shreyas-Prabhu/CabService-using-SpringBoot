package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarAssign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int txn_id;
	private String driver_email;
	private int car_id;
	private int isActive;
	
	
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(int txn_id) {
		this.txn_id = txn_id;
	}
	public String getDriver_email() {
		return driver_email;
	}
	public void setDriver_email(String driver_email) {
		this.driver_email = driver_email;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	
	
	
	
}
