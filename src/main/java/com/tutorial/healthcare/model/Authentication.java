package com.tutorial.healthcare.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="tbl_token")
public class Authentication {
	@Id
	@SequenceGenerator(name = "token_sequence",sequenceName = "token_sequence",allocationSize = 1,initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "token_sequence") 
	private Long tokenId;
	private String token;
	private LocalDate tokenCreationDate;
	
	
	
	@OneToOne
	@JoinColumn(nullable = false,name = "fk_patient_patientId")
	private Patient patient;



	public Authentication(Patient patient) {
		super();
		this.token = UUID.randomUUID().toString();
		this.tokenCreationDate = LocalDate.now();
		this.patient = patient;
	}
	
	
	

}
