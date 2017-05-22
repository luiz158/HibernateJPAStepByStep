package com.in28minutes.hibernate.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.hibernate.model.Student;

@Service
public class StudentService2 {

	@Autowired
	StudentService service;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Student insertStudent(Student student) {
		return service.insertStudent(student);
	}

}
