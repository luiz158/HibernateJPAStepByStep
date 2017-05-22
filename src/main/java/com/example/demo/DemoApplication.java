package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(DemoApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource datasource;

	@Autowired
	TodoJPAService todoJPAService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE todo IF EXISTS");

		jdbcTemplate.execute("CREATE TABLE todo("
				+ "id SERIAL, user VARCHAR(255), desc VARCHAR(255), target_date TIMESTAMP, is_done BOOLEAN)");

		jdbcTemplate
				.execute("DROP TABLE customers IF EXISTS");

		jdbcTemplate.execute("CREATE TABLE customers("
				+ "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays
				.asList("John Woo", "Jeff Dean",
						"Josh Bloch", "Josh Long")
				.stream().map(name -> name.split(" "))
				.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format(
				"Inserting customer record for %s %s",
				name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate(
				"INSERT INTO customers(first_name, last_name) VALUES (?,?)",
				splitUpNames);

		insertCustomer(new Customer("Ranga", "Karanam"));

		int todo1 = todoJPAService.addTodo(10, "Ranga",
				"Dummy10", new Date(), false);

		int todo2 = todoJPAService.addTodo(11, "Ranga",
				"Dummy11", new Date(), false);

		log.info(
				"Querying for customer records where first_name = 'Ranga':");
		jdbcTemplate
				.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
						new Object[] { "Ranga" },
						(rs, rowNum) -> new Customer(
								rs.getLong("id"),
								rs.getString("first_name"),
								rs.getString("last_name")))
				.forEach(customer -> log
						.info(customer.toString()));

		log.info(
				"Querying for todo records where user = 'Ranga':");

		todoJPAService.retrieveTodos("Ranga")
				.forEach(todo -> log.info(todo.toString()));

		todoJPAService.updateTodo(new Todo(todo1, "Ranga",
				"Dummy++", new Date(), false));

		log.info("Querying Todo by id " + todo1);

		log.info(todoJPAService.retrieveTodo(todo1)
				.toString());

		log.info("Deleting todo id " + todo2);

		todoJPAService.deleteTodo(todo2);

		log.info(
				"Querying for todo records where user = 'Ranga':");

		todoJPAService.retrieveTodos("Ranga")
				.forEach(todo -> log.info(todo.toString()));

	}

	private void insertCustomer(Customer customer)
			throws SQLException {

		Connection connection = datasource.getConnection();

		PreparedStatement st = connection.prepareStatement(
				"INSERT INTO customers(first_name, last_name) VALUES (?,?)");

		st.setString(1, customer.getFirstName());
		st.setString(2, customer.getLastName());

		st.execute();

		st.close();

		connection.close();
	}
}

