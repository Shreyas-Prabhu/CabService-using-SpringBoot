 package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.UserRequest;

import com.example.demo.Service.HomeService;
import com.example.demo.util.jwtutil;
import com.example.demo.Entity.UserResponse;

@RestController
public class HomeController { //This is the controller for Register and Login which is permited to all the users(without JWT token)
	
	@Autowired
	HomeService hService;
	
	@Autowired
	private jwtutil util;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")  //Registering the new user, role will be auto assigned as user
	public String registerUser(@RequestBody Customer cus)
	{
		return hService.Register(cus);
	}
	
	//This will generate new JWT token if the login is successful.
	@PostMapping("/authenticate")   
	public ResponseEntity<UserResponse> generateToken(@RequestBody UserRequest request) throws Exception
	{
		
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPass()));
		String token = util.generateToken(request.getEmail());
		return ResponseEntity.ok(new UserResponse(token,"Token Generated!!"));
	}
	
	//For testing Purpose
	@PostMapping("/hello")
	public ResponseEntity<String> accessData(Principal p)
	{
		return ResponseEntity.ok("Hello User"+p.getName());
	}
}
