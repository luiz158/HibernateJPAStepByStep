package com.in28minutes.hibernate.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.in28minutes.hibernate.model.Passport;
import com.in28minutes.hibernate.model.Student;

@Repository
public class StudentService {

	@PersistenceContext
	private EntityManager entityManager;

	public Student getStudent(final long id) {
		return entityManager.find(Student.class, id);
	}

	public Student insertStudent(Student student) {
		Passport passport = entityManager.merge(student.getPassport());
		Student persistedStudent = entityManager.merge(student);
		return persistedStudent;
	}

	public Student updateStudent(Student student) {
		entityManager.merge(student);
		return student;
	}

}
