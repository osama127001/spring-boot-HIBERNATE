package com.osama.springhibernate;

import com.osama.springhibernate.dao.OneToManyUniDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.persistence.OneToMany;
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
		OneToManyUniDao dao = new OneToManyUniDao();
		// dao.createCourseAndAddReviews();
		dao.getCourseAndReviews(10);
	}

}