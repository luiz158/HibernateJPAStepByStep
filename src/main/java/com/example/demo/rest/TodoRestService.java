package com.example.demo.rest;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.data.todo.TodoJPAService;
import com.example.demo.entity.Todo;

@RestController
public class TodoRestService {

	@Autowired
	TodoJPAService todoJPAService;

	@GetMapping("/todos/user/{user}")
	public List<Todo> retrieveTodos(
			@PathVariable String user) throws SQLException {
		return todoJPAService.retrieveTodos(user);
	}

	@PostMapping("/todos")
	public ResponseEntity<Void> addTodo(
			@RequestBody Todo todo) throws SQLException {
		int todoID = todoJPAService.addTodo(todo.getUser(),
				todo.getDesc(), todo.getTargetDate(),
				todo.isDone());

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(todoID).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/todos/{id}")
	public Todo retrieveTodo(@PathVariable int id)
			throws SQLException {
		return todoJPAService.retrieveTodo(id);
	}

	@PutMapping("/todos")
	public void updateTodo(@RequestBody Todo todo)
			throws SQLException {
		todoJPAService.updateTodo(todo);
	}

	@DeleteMapping("/todos/{id}")
	public void deleteTodo(@PathVariable int id)
			throws SQLException {
		todoJPAService.deleteTodo(id);
	}
}

