package com.tutorial.healthcare.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.healthcare.doa.SignInInput;
import com.tutorial.healthcare.doa.SignInOutput;
import com.tutorial.healthcare.doa.SignUpInput;
import com.tutorial.healthcare.doa.SignUpOutput;
import com.tutorial.healthcare.dto.AppoinmentDto;
import com.tutorial.healthcare.model.Appointment;
import com.tutorial.healthcare.model.Authentication;
import com.tutorial.healthcare.model.Doctor;
import com.tutorial.healthcare.model.Patient;
import com.tutorial.healthcare.repository.AppointmentRepo;
import com.tutorial.healthcare.repository.AuthenticationRepo;
import com.tutorial.healthcare.repository.DoctorRepo;
import com.tutorial.healthcare.repository.PatientRepo;


@Service
public class PatientService {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private AuthenticationRepo authRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private AppointmentRepo appoRepo;
	
	
	
	

	public SignUpOutput signUp(SignUpInput input) {
		
		Patient patient=patientRepo.findByPatientEmail(input.getUserEmail());
		if(patient!=null) {
			
			return new SignUpOutput("User already exist");
			
		}
		
		String password=input.getUserPassword();
		
		String encryptedpassword=encryptPassword(password);
		
		patient=Patient.builder().patientContact(input.getUserContact()).patientEmail(input.getUserEmail()).patientName(input.getUserName())
				.patientpassword(encryptedpassword).build();
		
		patientRepo.save(patient);
		
		
		
		return new SignUpOutput("Sign up sucessfully");
	
		
		
		
	}
	
	

	   private String encryptPassword(String password) {
	       
     String salt = BCrypt.gensalt();

     
     String hashedPassword = BCrypt.hashpw(password, salt);

     return hashedPassword;
 }



	public SignInOutput signIn(SignInInput signInDto) {
	
		 Patient patient = patientRepo.findByPatientEmail(signInDto.getEmail());
		 
		 if(patient==null) {
			 return new SignInOutput("email not found","ask for help");
		 }
		 
		 String password=signInDto.getPassword();
		 String encryptedPassword=patient.getPatientpassword();
		 
		 boolean check=verifyPassword(password,encryptedPassword);
		 
		 if(check==false) {
			 
			 
			 return new SignInOutput("sign in failed","ask for help");
		 }
		
		 Authentication auth=new Authentication(patient);
		 authRepo.save(auth);
		
		
		
		return new SignInOutput("sign in sucess",auth.getToken());
	}
	
	
	
	  
	   private boolean verifyPassword(String password, String hashedPassword) {
	    return BCrypt.checkpw(password, hashedPassword);
	}



	public List<Doctor> getAlldoctors(String token, String email) {
		
		List<Doctor>allDoctors=new ArrayList<>();
		Patient patient=patientRepo.findByPatientEmail(email);
		if(patient==null) {
			
			return allDoctors;
		}
		
		Authentication auth=authRepo.findByToken(token);
		if(auth==null) {
			return allDoctors;
		}
		Long patientId=patient.getPatientId();
		Long authFkpatientId=auth.getPatient().getPatientId();
		if(patientId!=authFkpatientId) {
			return allDoctors;
		}
		
		
		
		
		allDoctors=doctorRepo.findAll();
		return allDoctors;
	}



	public ResponseEntity<String> appoinment(String token, String email, AppoinmentDto input) {
		
		Authentication auth=authRepo.findByToken(token);
		Long authFkpatientId=auth.getPatient().getPatientId();
		
		Patient patient=patientRepo.findById(authFkpatientId).get();
		
		Doctor doctor=doctorRepo.findById(input.getDoctorId()).get();
		Appointment appointment=Appointment.builder().doctor(doctor).patient(patient).build();
		
		appoRepo.save(appointment);
		
		
		return new ResponseEntity<String>("booked appoinment",HttpStatus.ACCEPTED);
	}
	

}
	

	


