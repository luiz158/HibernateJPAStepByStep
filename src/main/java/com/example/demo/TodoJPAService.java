package com.example.demo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TodoJPAService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Todo> retrieveTodos(String user) {
		Query query = entityManager.createNamedQuery(
				"find all todos for user", Todo.class);
		query.setParameter(1, user);
		return query.getResultList();

	}

	public int addTodo(int id, String user, String desc,
			Date targetDate, boolean isDone)
					throws SQLException {
		Todo todo = entityManager.merge(
				new Todo(user, desc, targetDate, isDone));
		return todo.getId();
	}

	public Todo retrieveTodo(int id) throws SQLException {
		return entityManager.find(Todo.class, id);
	}

	public void updateTodo(Todo todo) {
		entityManager.merge(todo);
	}

	public void deleteTodo(int id) {
		// TODO
	}

}

