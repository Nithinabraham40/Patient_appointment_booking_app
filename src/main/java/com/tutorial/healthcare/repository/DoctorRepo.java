package com.tutorial.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.healthcare.model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	
	
	

}
