package com.example.demo.data.student;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Student retrieveStudent(final long id) {
		return entityManager.find(Student.class, id);
	}

	public Student createStudent(Student student) {
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

	public List<Student> retrieveAllStudents() {
		Query query = entityManager
				.createNamedQuery("find all students");
		return query.getResultList();
	}
}

