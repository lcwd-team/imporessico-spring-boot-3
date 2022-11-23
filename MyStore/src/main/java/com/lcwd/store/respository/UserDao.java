package com.lcwd.store.respository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lcwd.store.entities.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(UserDao.class);

	public UserDao(JdbcTemplate template) {
		this.jdbcTemplate = template;
		logger.info("Template Created {}", jdbcTemplate);
		// create table: if not exits
		String queryString = "create table IF NOT EXISTS jdbc_user(id int primary key,name varchar(50),email varchar(50), password varchar(50),about varchar(500), gender varchar(10))";
		jdbcTemplate.update(queryString);
	}

	// create user
	public User createUser(User user) {
		String querString = "insert into jdbc_user(id,name,email,password,about,gender) values(?,?,?,?,?,?)";

		int update = jdbcTemplate.update(querString, user.getId(), user.getName(), user.getEmail(), user.getPassword(),
				user.getAbout(), user.getGender());
		logger.info("{} rows added", update);
		return user;
	}

	// get all
	public List<User> getAllUsers() {
		String queryString = "select * from jdbc_user";
		List<User> users = jdbcTemplate.query(queryString, new UserRowMapper());
		return users;

		// List<Map<String, Object>> listOfUsers =
		// jdbcTemplate.queryForList(queryString);

		// // Manully: convert all the map object to user object:
		// List<User> listUsers = listOfUsers.stream().map(m -> {
		// User user = new User();
		// user.setId(Integer.parseInt(m.get("id").toString()));
		// user.setName(m.get("name").toString());
		// user.setEmail(m.get("email").toString());
		// user.setPassword(m.get("password").toString());
		// user.setAbout(m.get("about").toString());
		// user.setGender(m.get("gender").toString());
		// return user;

		// }).collect(Collectors.toList());

		// return listUsers;
	}

	// get single user data:
	public User getUser(int userId) {
		String query = "select * from jdbc_user WHERE id = ?";
		User user = jdbcTemplate.queryForObject(query, new UserRowMapper(), userId);
		return user;
	}

}
