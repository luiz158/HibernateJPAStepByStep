package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoJdbcService {

	@Autowired
	DataSource datasource;

	// Think about exception handling
	// We are explicitly getting the connection! What if there is an
	// exception while executing the query!

	public List<Todo> retrieveTodos(String user)
			throws SQLException {
		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"SELECT * FROM TODO where user=?");

		st.setString(1, user);

		ResultSet resultSet = st.executeQuery();
		List<Todo> todos = new ArrayList<>();

		while (resultSet.next()) {

			Todo todo = new Todo(resultSet.getInt("id"),
					resultSet.getString("user"),
					resultSet.getString("desc"),
					resultSet.getTimestamp("target_date"),
					resultSet.getBoolean("is_done"));
			todos.add(todo);
		}

		st.close();

		connection.close();

		return todos;

	}

	public void addTodo(int id, String user, String desc,
			Date targetDate, boolean isDone)
					throws SQLException {
		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"INSERT INTO todo(id, user, desc, targetDate, isDone) VALUES (?,?,?,?,?)");

		st.setInt(1, id);
		st.setString(2, user);
		st.setString(3, desc);
		st.setTimestamp(4,
				new Timestamp(targetDate.getTime()));
		st.setBoolean(5, isDone);

		st.execute();

		st.close();

		connection.close();

	}

	public Todo retrieveTodo(int id) throws SQLException {
		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"SELECT * FROM TODO where id=?");

		st.setInt(1, id);

		ResultSet resultSet = st.executeQuery();

		Todo todo = null;

		if (resultSet.next()) {

			todo = new Todo(resultSet.getInt("id"),
					resultSet.getString("user"),
					resultSet.getString("desc"),
					resultSet.getTimestamp("target_date"),
					resultSet.getBoolean("is_done"));

		}

		st.close();

		connection.close();

		return todo;

	}

	public void updateTodo(Todo todo) throws SQLException {
		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"Update todo set user=?, desc=?, targetDate=?, isDone=? where id=?");

		st.setString(1, todo.getUser());
		st.setString(2, todo.getDesc());
		st.setTimestamp(3, new Timestamp(
				todo.getTargetDate().getTime()));
		st.setBoolean(4, todo.isDone());
		st.setInt(5, todo.getId());

		st.execute();

		st.close();

		connection.close();

	}

	public void deleteTodo(int id) throws SQLException {
		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"delete from todo where id=?");

		st.setInt(1, id);

		st.execute();

		st.close();

		connection.close();

	}

}

