package com.in28minutes.hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.hibernate.model.Student;

@Service
public class StudentService {

	@Autowired
	StudentRepository service;

	@Transactional
	public Student insertStudent(Student student) {
		return service.insertStudent(student);
	}

	@Transactional
	public Student getStudent(final long id) {
		return service.getStudent(id);
	}

	@Transactional
	public Student updateStudent(Student student) {
		return service.updateStudent(student);
	}

	@Transactional
	public Student retrieveIndianStudents() {
		return service.retrieveStudentsFrom("India");
	}

	@Transactional
	public List<Student> getAllStudents() {
		return service.getAllStudents();
	}

}

