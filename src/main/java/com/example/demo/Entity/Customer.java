package com.example.demo.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	private String email;
	private String fname;
	private String lname;
	private String password;
	private long phone;
	
	private String role;
	
	@Column(columnDefinition = "integer default 0")
	private int isDriverAvailable;
	@Column(columnDefinition = "integer default 1")
	private int isCustomerFree;
	
	public int getIsCustomerFree() {
		return isCustomerFree;
	}
	public void setIsCustomerFree(int isCustomerFree) {
		this.isCustomerFree = isCustomerFree;
	}
	public int getIsDriverAvailable() {
		return isDriverAvailable;
	}
	public void setIsDriverAvailable(int isDriverAvailable) {
		this.isDriverAvailable = isDriverAvailable;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Customer [email=" + email + ", fname=" + fname + ", lname=" + lname + ", role=" + role + "]";
	}

	

	
	


}
