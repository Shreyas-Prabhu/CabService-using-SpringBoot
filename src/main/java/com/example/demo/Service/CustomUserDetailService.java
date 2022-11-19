package com.example.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.config.MyUserDetails;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	CustomerRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Customer cus = repo.findByEmail(username);
		if(cus == null)
			throw new UsernameNotFoundException("User not exist");
		return new MyUserDetails(cus);
	}
	
	
}
