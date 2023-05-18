# Welcome to readme-md-generator &#x1F44B; 
![example workflow](https://img.shields.io/badge/Eclipse-Version%3A%202022--09%20(4.25.0)-orange)
![example workflow](https://img.shields.io/badge/SpringBoot-2.2.1-yellowgreen)
![example workflow](https://img.shields.io/badge/Java-8-yellowgreen)
![example workflow](https://img.shields.io/badge/Postman-v10.13-orange)
![example workflow](https://img.shields.io/badge/Documentation-Yes-green)
![example workflow](https://img.shields.io/badge/Manitained%3F-Yes-green)
 >CLI that generate beautiful **ReadME**.md files

  :house:  <b><span style="color: blue;">Homepage</span></b>
  


 # Prerequisties

 - **Eclipse >=4.55.0**
 - **Postman >=10.13**
 


# Install
```
Maven Install
SpringTool Install
```
 # Framework And Language

 - **Framework :  SpringBoot**
 - **Language :  Java**

 # Dependencies Required

 
 - **spring-boot-starter-web**
 - **spring-boot-devtools**
 - **spring-boot-starter-data-jpa**
 - **mysql-connector**
 - **lambok**
 - **jbcrypt**

 - **spring-boot-starter-test**
 


# Model Used



 - **Appointment**
 -  **Patient**
 -  **Doctor**
 -  **Authentication**
 -  **Specialization**


	
	



#  Patient Controller


```

@RestController
@RequestMapping("/patient")
public class PatientController {

	
	@Autowired
	
	
	private PatientService patientService;
	
	@PostMapping("/signup")
	
	public SignUpOutput signUp(@RequestBody SignUpInput input){
		
		
		return patientService.signUp(input);
	}
	
	 @PostMapping("/signin")
	    public SignInOutput signIn(@RequestBody SignInInput signInDto)
	    {
	        return patientService.signIn(signInDto);
	        
	    }
	 @GetMapping("get/alldoctors/{token}/{email}")
	 public List<Doctor>getAlldoctors(@PathVariable("token")String token,@PathVariable("email")String email){
		 
		 
		 return patientService.getAlldoctors(token,email);
	 }
	 @PostMapping("book/appoinment/{token}/{email}")
	 
	 public ResponseEntity<String>appoinment(@PathVariable("token")String token,@PathVariable ("email") String email,@RequestBody AppoinmentDto input){
		 
		 
		 return patientService.appoinment(token,email,input);
		 
	 }
		 
		 
		 
	 }
	
```

#  Patient Service


```
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
		
		Patient patient=patientRepo.findByPatientEmail(email);
		if(patient==null) {
			
			return new ResponseEntity<String>("authentication fail",HttpStatus.BAD_GATEWAY);
		}
		Authentication auth=authRepo.findByToken(token);
		if(auth==null) {
			return new ResponseEntity<String>("authentication fail",HttpStatus.BAD_GATEWAY);
		}
		Long patientId=patient.getPatientId();
		Long authFkpatientId=auth.getPatient().getPatientId();
		if(patientId!=authFkpatientId) {
			return new ResponseEntity<String>("authentication fail",HttpStatus.BAD_GATEWAY);
		}
		
		
	
		
		 patient=patientRepo.findById(authFkpatientId).get();
		
		Doctor doctor=doctorRepo.findById(input.getDoctorId()).get();
		Appointment appointment=Appointment.builder().doctor(doctor).patient(patient).build();
		
		appoRepo.save(appointment);
		
		
		return new ResponseEntity<String>("booked appoinment",HttpStatus.ACCEPTED);
	}
	

}
	
```


#  Patient Repository


```
public interface PatientRepo extends JpaRepository<Patient, Long> {



	

	Patient findByPatientEmail(String email);

}


	
```


#  Doctor Controller


```
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	
	
	@Autowired
	private DoctorService doctorService;
	
	
	@PostMapping("/add")
	public ResponseEntity<String>addDoctor(@RequestBody DoctorDto input){
		
		
		return doctorService.addDoctor(input);
		
		
		
	}

}


	
```


#  Doctor Service


```
@Autowired
	
	private DoctorRepo doctorRepo;
	
	public ResponseEntity<String> addDoctor(DoctorDto input) {
		
		Doctor doctor=new Doctor();
		
		
		doctor=Doctor.builder().doctorName(input.getDoctorName()).specialization(input.getSpecialization()).build();
		
		
		doctorRepo.save(doctor);
		
		
	
		return new ResponseEntity<String>("sucessfully saved",HttpStatus.ACCEPTED);
	}




	
```


#  Doctor Repository


```
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	
}

	
```

	
	


  


	







	



# DataBase Used

<details>
<summary><b><span style="color: white;">Clickme</span></b> &#x1F4F2; </summary>

*Mysql*



</details>



  




# Summary

 Spring boot Patient appointment project using Mysql us database and proper authentictaions .
These project will have the following features
**addPatient**,
**adddoctors**,
**bookAppointment**,
**selectdoctor**
.






# :handshake:  Contributing
  Contributions,issues and features request are welcome! 
  

  #


  This *README* was generated with &#x2764;&#xFE0F; by <b><span style="color: blue;">readme-md-generator</span></b> 










   
  
  

