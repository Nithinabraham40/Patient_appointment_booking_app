package com.tutorial.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.healthcare.model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long>{
	

}
