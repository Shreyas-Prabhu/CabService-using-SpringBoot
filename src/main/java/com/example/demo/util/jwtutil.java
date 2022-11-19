package com.example.demo.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtutil {  //This is the class for generating JWT token and other details

	@Value("${app.secret}")
	private String secret;
	
	//Generate Token
		public String generateToken(String subject)  //Here subject is username/email
		{
			return Jwts.builder()
					.setSubject(subject)
					.setIssuer("shreyas")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1))) //1 Day expiration set
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.compact();
		}
		
		//Read Claims
		public Claims getClaims(String token)
		{
			return Jwts.parser()
					.setSigningKey(secret.getBytes())
					.parseClaimsJws(token)
					.getBody();
		}
		
		//Read Expiry date
		public Date getExpDate(String token)
		{
			return getClaims(token).getExpiration();
		}
		
		//Validate Expiry date
		public boolean isTokenExp(String token)
		{
			Date expDate=getExpDate(token);
			return expDate.before(new Date(System.currentTimeMillis()));
		}
	
		//Read Subject or email
		public String getUsername(String token)
		{
			return getClaims(token).getSubject();
		}
		
	
	//validate email in token and in database and also expDate
	public boolean validateToken(String token, String user)
	{
		String tokenUserName = getUsername(token);
		return (user.equals(tokenUserName) && !isTokenExp(token));
	}
	
	
	
	
	

	
	
	
}
