package com.tutorial.healthcare.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.healthcare.dto.DoctorDto;
import com.tutorial.healthcare.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	
	
	@Autowired
	private DoctorService doctorService;
	
	
	@PostMapping("/add")
	public ResponseEntity<String>addDoctor(@RequestBody DoctorDto input){
		
		
		return doctorService.addDoctor(input);
		
		
		
	}

}
