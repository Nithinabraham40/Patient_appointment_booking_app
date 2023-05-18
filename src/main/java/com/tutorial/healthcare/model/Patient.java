package com.tutorial.healthcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tbl_patient")
public class Patient {
	
	
	@Id
	@SequenceGenerator(name = "patient_sequence",sequenceName = "patient_sequence",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_sequence") 
	private Long patientId;
	private String patientName;
	private String patientEmail;
	private String patientpassword;
	private String patientContact;
	
	public Patient(String patientName, String patientEmail, String patientpassword, String patientContact) {
		super();
		this.patientName = patientName;
		this.patientEmail = patientEmail;
		this.patientpassword = patientpassword;
		this.patientContact = patientContact;
	}
	
	

}
