package com.tutorial.healthcare.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tbl_appointment")
public class Appointment {

	@Id
	@SequenceGenerator(name = "appointment_sequence",sequenceName = "appointment_sequence",allocationSize = 1,initialValue = 7000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_sequence") 
	private Long appoId;
	
	
	    @ManyToOne
	    @JoinColumn(name = "fk_doctor_doc_id")
	    private Doctor doctor;

	    @OneToOne
	    private Patient patient;
}
