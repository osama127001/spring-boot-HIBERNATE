package com.osama.springhibernate;

import com.osama.springhibernate.dao.InstructorDao;
import com.osama.springhibernate.dao.StudentDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateApplication.class, args);

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_instructor?useSSL=false", "root", "admin");
			System.out.println("Connection Successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Testing Code
		InstructorDao iDao = new InstructorDao();
//		iDao.createInstructor("Hadi", "Khan", "hadi@gmail.com", "http://hadi.com", "Movie Geek");
//		iDao.createInstructor("Aamir", "Hanif", "aamir@gmail.com", "http://aamir.com", "Cricket");
		iDao.deleteInstructor(1);
	}

}