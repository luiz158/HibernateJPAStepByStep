package com.in28minutes.hibernate.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.in28minutes.hibernate.model.Student;

@Repository
public class StudentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Student getStudent(final long id) {
		return entityManager.find(Student.class, id);
	}

	public Student insertStudent(Student student) {
		if (student.getPassport() != null)
			entityManager.merge(student.getPassport());
		return entityManager.merge(student);
	}

	public Student updateStudent(Student student) {
		entityManager.merge(student);
		return student;
	}

	public Student retrieveStudentsFrom(String string) {
		return null;
	}

	public List<Student> getAllStudents() {
		Query query = entityManager.createNamedQuery("find all students");
		return query.getResultList();
	}
}

