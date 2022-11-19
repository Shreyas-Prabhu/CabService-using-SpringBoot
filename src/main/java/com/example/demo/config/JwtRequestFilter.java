package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Service.CustomUserDetailService;
import com.example.demo.util.jwtutil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {   // this is a jwt filter which checks whether the same person is requesting who has
																//logged in

	@Autowired
	private CustomUserDetailService cudService;

	@Autowired
	private jwtutil util;
	


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorizationHeader = request.getHeader("Authorization"); //this key is used in header in postman
		String token = null;
		String email = null;
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
		{
			token=authorizationHeader.substring(7); //Bearer -->After this the token is present
			email = util.getUsername(token);
		}
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			MyUserDetails userDetails =  (MyUserDetails) cudService.loadUserByUsername(email);
			  if (util.validateToken(token, userDetails.getUsername())) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            // System.out.println(userDetails.getAuthorities());
	            }
			 
		 }
		 filterChain.doFilter(request, response);
	}

}
