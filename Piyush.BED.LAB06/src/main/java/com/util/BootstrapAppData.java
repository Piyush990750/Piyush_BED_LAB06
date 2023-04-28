package com.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.entity.Role;
import com.entity.Student;
import com.entity.User;
import com.repository.StudentRepository;
import com.repository.UserRepository;



@Component
public class BootstrapAppData {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertStudentData(ApplicationReadyEvent event) {
		
		// Creating Dummy data in database
		Student student = new Student();
		student.setFirstName("Suresh");
		student.setLastName("Reddy");
		student.setCourse("B.tech");
		student.setCountry("India");
		
		Student student1 = new Student();
		student1.setFirstName("Murali");
		student1.setLastName("Mohan");
		student1.setCourse("B.Arch");
		student1.setCountry("Canada");
		
		Student student2 = new Student();
		student2.setFirstName("Deniel");
		student2.setLastName("Denson");
		student2.setCourse("B.Tech");
		student2.setCountry("New ZeaLand");
		
		Student student3 = new Student();
		student3.setFirstName("Tanay");
		student3.setLastName("Gupta");
		student3.setCourse("B.Tech");
		student3.setCountry("USA");
		
		
		this.studentRepository.save(student);
		this.studentRepository.save(student1);
		this.studentRepository.save(student2);
		this.studentRepository.save(student3);
		
		
	}

	
	@EventListener(ApplicationReadyEvent.class)
	public void insertRolesData(ApplicationReadyEvent event) {
		
		// Creating application ready users in database
		User user1 = new User();
		user1.setUsername("user");
		user1.setPassword(passwordEncoder1().encode("user"));
		
		User user2 = new User();
		user2.setUsername("Admin");
		user2.setPassword(passwordEncoder1().encode("Admin"));
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");		

		user1.addRole(userRole);
		user2.addRole(adminRole);
		
		this.userRepository.save(user1);
		this.userRepository.save(user2);
		
	}


}
