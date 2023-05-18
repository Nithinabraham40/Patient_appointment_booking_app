package com.tutorial.healthcare.dto;


import com.tutorial.healthcare.model.Specialization;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

	
	private String doctorName;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    
    
}
