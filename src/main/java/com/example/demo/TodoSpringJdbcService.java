package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TodoSpringJdbcService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// Think about exception handling
	// We are explicitly getting the connection! What if there is an
	// exception while executing the query!

	// new BeanPropertyRowMapper(TodoMapper.class)
	class TodoMapper implements RowMapper<Todo> {
		@Override
		public Todo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Todo todo = new Todo();

			todo.setId(rs.getInt("id"));
			todo.setUser(rs.getString("user"));
			todo.setDesc(rs.getString("desc"));
			todo.setTargetDate(
					rs.getTimestamp("target_date"));
			todo.setDone(rs.getBoolean("is_done"));
			return todo;
		}
	}

	public List<Todo> retrieveTodos(String user) {
		return jdbcTemplate.query(
				"SELECT * FROM TODO where user = ?",
				new Object[] { user }, new TodoMapper());

	}

	public void addTodo(int id, String user, String desc,
			Date targetDate, boolean isDone)
					throws SQLException {

		jdbcTemplate.update(
				"INSERT INTO todo(id, user, desc, targetDate, isDone) VALUES (?,?,?,?,?)",
				id, user, desc, targetDate, isDone);
	}

	public Todo retrieveTodo(int id) throws SQLException {

		return jdbcTemplate.queryForObject(
				"SELECT * FROM TODO where id=?",
				new Object[] { id }, new TodoMapper());

	}

	public void updateTodo(Todo todo) {
		jdbcTemplate
				.update("Update todo set user=?, desc=?, targetDate=?, isDone=? where id=?",
						todo.getUser(), todo.getDesc(),
						new Timestamp(todo.getTargetDate()
								.getTime()),
						todo.isDone(), todo.getId());

	}

	public void deleteTodo(int id) {
		jdbcTemplate.update("delete from todo where id=?",
				id);
	}

}

