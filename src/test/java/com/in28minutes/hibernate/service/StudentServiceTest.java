package com.in28minutes.hibernate.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.hibernate.model.Passport;
import com.in28minutes.hibernate.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/spring-context.xml" })
@Service
public class StudentServiceTest {

	@Autowired
	private StudentService service;

	@Autowired
	private StudentService2 service2;

	@Test
	@Transactional
	public void testGetStudent() {
		Student student = service.getStudent(101);
		System.out.println(student);
		assertNotNull(student);
		assertEquals(101, student.getId());
	}

	@Test
	@Transactional
	public void testUpdateStudent() {
		Student student = service.getStudent(101);
		student.setName("Doe v2");
		Student insertedStudent = service.updateStudent(student);
		Student retrievedStudent = service.getStudent(insertedStudent.getId());
		System.out.println(student);
		assertNotNull(retrievedStudent);
	}

	@Test
	public void testInsertStudent() {
		Passport passport = new Passport(202, "L12344432", "India");
		Student student = createStudent("dummy@dummy.com", "Doe", passport);

		Student insertedStudent = service2.insertStudent(student);
		Student retrievedStudent = service.getStudent(insertedStudent.getId());
		assertNotNull(retrievedStudent);
	}

	private Student createStudent(String email, String name, Passport passport) {
		Student student = new Student();
		student.setEmail(email);
		student.setName(name);
		student.setPassportId(passport);
		return student;
	}
}

