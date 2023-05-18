package com.tutorial.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.healthcare.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {



	

	Patient findByPatientEmail(String email);

}
