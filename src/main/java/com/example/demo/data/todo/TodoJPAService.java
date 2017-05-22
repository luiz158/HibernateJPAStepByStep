package com.example.demo.data.todo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Todo;

@Repository
@Transactional
public class TodoJPAService implements TodoDataService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Todo> retrieveTodos(String user) {
		Query query = entityManager.createNamedQuery(
				"find all todos for user", Todo.class);
		query.setParameter(1, user);
		return query.getResultList();

	}

	@Override
	public int addTodo(String user, String desc,
			Date targetDate, boolean isDone) {
		Todo todo = entityManager.merge(
				new Todo(user, desc, targetDate, isDone));
		return todo.getId();
	}

	@Override
	public Todo retrieveTodo(int id) {
		return entityManager.find(Todo.class, id);
	}

	@Override
	public void updateTodo(Todo todo) {
		entityManager.merge(todo);
	}

	@Override
	public void deleteTodo(int id) {
		Todo todo = retrieveTodo(id);
		entityManager.remove(todo);
	}
}

