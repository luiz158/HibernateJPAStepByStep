package com.example.demo.data.todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Todo;

@Component
public class TodoSpringJdbcService
		implements TodoDataService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	SimpleJdbcInsert insertTodo;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.insertTodo = new SimpleJdbcInsert(dataSource)
				.withTableName("TODO")
				.usingGeneratedKeyColumns("id");
	}

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

	@Override
	public List<Todo> retrieveTodos(String user) {
		return jdbcTemplate.query(
				"SELECT * FROM TODO where user = ?",
				new Object[] { user }, new TodoMapper());

	}

	@Override
	public int addTodo(String user, String desc,
			Date targetDate, boolean isDone)
					throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("desc", desc);
		params.put("target_date", targetDate);
		params.put("is_done", isDone);
		Number id = insertTodo.executeAndReturnKey(params);

		return id.intValue();
	}

	@Override
	public Todo retrieveTodo(int id) {

		return jdbcTemplate.queryForObject(
				"SELECT * FROM TODO where id=?",
				new Object[] { id }, new TodoMapper());

	}

	@Override
	public void updateTodo(Todo todo) {
		jdbcTemplate
				.update("Update todo set user=?, desc=?, target_date=?, is_done=? where id=?",
						todo.getUser(), todo.getDesc(),
						new Timestamp(todo.getTargetDate()
								.getTime()),
						todo.isDone(), todo.getId());

	}

	@Override
	public void deleteTodo(int id) {
		jdbcTemplate.update("delete from todo where id=?",
				id);
	}

}

