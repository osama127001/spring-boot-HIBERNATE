package com.osama.springhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateApplication.class, args);

		/*
		* Database: MySQL
		* Testing Database Connection
		*/
		String userName = "dbadmin";
		String password = "admin";
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		try {
			System.out.println("Connecting to database");
			Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
			System.out.println("Connection Successful");
		} catch (Exception exception) {
			exception.printStackTrace();
		}


	}

}
