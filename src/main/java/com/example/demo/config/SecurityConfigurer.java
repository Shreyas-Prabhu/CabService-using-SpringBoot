package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.Service.CustomUserDetailService;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer{
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtFilter;



	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     
	    authProvider.setUserDetailsService(this.userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	 
	    return authProvider;
	}
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     
		  	http.cors().disable();
		 	http.csrf().disable();
	        http.authorizeRequests().antMatchers("/authenticate").permitAll()
	        .antMatchers("/register").permitAll().antMatchers("/admin/**").hasAnyAuthority("admin")
	        .antMatchers("/user/**").hasAnyAuthority("admin","user","driver")
	        .antMatchers("/driver/**").hasAnyAuthority("driver")
	        .anyRequest().authenticated()
	        .and().exceptionHandling()
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	        return http.build();
	    }
	 
}
