package com.example.demo.data.todo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.Todo;

public interface TodoDataService {

	public List<Todo> retrieveTodos(String user)
			throws SQLException;

	public int addTodo(String user, String desc,
			Date targetDate, boolean isDone)
					throws SQLException;

	public Todo retrieveTodo(int id) throws SQLException;

	public void updateTodo(Todo todo) throws SQLException;

	public void deleteTodo(int id) throws SQLException;
}

