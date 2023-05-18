package com.tutorial.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.healthcare.dto.DoctorDto;
import com.tutorial.healthcare.model.Doctor;
import com.tutorial.healthcare.repository.DoctorRepo;
@Service
public class DoctorService {

	
	@Autowired
	
	private DoctorRepo doctorRepo;
	
	public ResponseEntity<String> addDoctor(DoctorDto input) {
		
		Doctor doctor=new Doctor();
		
		
		doctor=Doctor.builder().doctorName(input.getDoctorName()).specialization(input.getSpecialization()).build();
		
		
		doctorRepo.save(doctor);
		
		
	
		return new ResponseEntity<String>("sucessfully saved",HttpStatus.ACCEPTED);
	}

}
