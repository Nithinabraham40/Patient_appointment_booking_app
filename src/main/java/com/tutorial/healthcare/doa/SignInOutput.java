package com.tutorial.healthcare.doa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SignInOutput {
	
	
	String status;
	String token;
	
	

}
