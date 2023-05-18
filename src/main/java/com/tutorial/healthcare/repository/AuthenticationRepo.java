package com.tutorial.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.healthcare.model.Authentication;

public interface AuthenticationRepo extends JpaRepository<Authentication, Long>{

	Authentication findByToken(String token);
	

}
