package com.tutorial.healthcare.doa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpInput {
	
	
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userContact;

}
