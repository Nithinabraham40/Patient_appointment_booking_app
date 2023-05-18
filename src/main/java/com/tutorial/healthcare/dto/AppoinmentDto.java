package com.tutorial.healthcare.dto;

import com.tutorial.healthcare.model.Specialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppoinmentDto {
	
	
	private Long doctorId;


}
