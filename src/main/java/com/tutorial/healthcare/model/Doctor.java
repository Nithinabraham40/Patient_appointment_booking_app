package com.tutorial.healthcare.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="tbl_doctor")
public class Doctor {
	
	
	@Id
	@SequenceGenerator(name = "doctor_sequence",sequenceName = "doctor_sequence",allocationSize = 1,initialValue = 5000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_sequence") 
	private Long doctorId;

    private String doctorName;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Appointment> appointments;

}
