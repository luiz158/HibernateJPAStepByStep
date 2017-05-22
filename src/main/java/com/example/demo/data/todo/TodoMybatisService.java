package com.example.demo.data.todo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Todo;

@Mapper
public interface TodoMybatisService
		extends TodoDataService {

	@Override
	@Insert("INSERT INTO TODO(user, desc, target_date, is_done) VALUES (#{user}, #{desc}, #{targetDate}, #{isDone})")
	public int addTodo(@Param("user") String user,
			@Param("desc") String desc,
			@Param("targetDate") Date targetDate,
			@Param("isDone") boolean isDone)
					throws SQLException;

	@Override
	@Select("SELECT * FROM TODO WHERE id = #{id}")
	public Todo retrieveTodo(int id) throws SQLException;

	@Override
	@Update("Update todo set user=#{user}, desc=#{desc}, target_date=#{targetDate}, is_done=#{isDone} where id=#{id}")
	public void updateTodo(Todo todo) throws SQLException;

	@Override
	@Delete("DELETE FROM TODO WHERE id = #{id}")
	public void deleteTodo(int id);

	@Override
	@Select("SELECT * FROM TODO WHERE user = #{user}")
	List<Todo> retrieveTodos(@Param("user") String user);

}

