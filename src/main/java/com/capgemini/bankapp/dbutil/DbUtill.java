package com.capgemini.bankapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")

public class DbUtill {
	@Autowired
	static Environment env;
	 public String geturl()
	 {
		return env.getProperty("dburl");
	 }
	 public String getUsername()
	 {
		return env.getProperty("Username");
	 }
	 public String getpassword()
	 {
		return env.getProperty("password");
	 }
	 
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(env.getProperty("dburl"), env.getProperty("Username"), env.getProperty("password"));
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}

	