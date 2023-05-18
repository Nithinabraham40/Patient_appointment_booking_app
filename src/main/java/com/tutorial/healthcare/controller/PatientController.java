package com.tutorial.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.healthcare.doa.SignInInput;
import com.tutorial.healthcare.doa.SignInOutput;
import com.tutorial.healthcare.doa.SignUpInput;
import com.tutorial.healthcare.doa.SignUpOutput;
import com.tutorial.healthcare.dto.AppoinmentDto;
import com.tutorial.healthcare.model.Doctor;
import com.tutorial.healthcare.services.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	
	@Autowired
	
	
	private PatientService patientService;
	
	@PostMapping("/signup")
	
	public SignUpOutput signUp(@RequestBody SignUpInput input){
		
		
		return patientService.signUp(input);
	}
	
	 @PostMapping("/signin")
	    public SignInOutput signIn(@RequestBody SignInInput signInDto)
	    {
	        return patientService.signIn(signInDto);
	        
	    }
	 @GetMapping("get/alldoctors/{token}/{email}")
	 public List<Doctor>getAlldoctors(@PathVariable("token")String token,@PathVariable("email")String email){
		 
		 
		 return patientService.getAlldoctors(token,email);
	 }
	 @PostMapping("book/appoinment/{token}/{email}")
	 
	 public ResponseEntity<String>appoinment(@PathVariable("token")String token,@PathVariable ("email") String email,@RequestBody AppoinmentDto input){
		 
		 
		 return patientService.appoinment(token,email,input);
		 
	 }
		 
		 
		 
	 }
	 
	 
	 
		
		
		
		
	
	
	

