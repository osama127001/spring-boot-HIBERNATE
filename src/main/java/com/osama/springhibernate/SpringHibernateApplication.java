package com.osama.springhibernate;

import com.osama.springhibernate.dao.OneToManyDao;
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
		OneToManyDao ODao = new OneToManyDao();
//		ODao.createInstructor("Fahad", "Raza", "fraza@gmail.com", "http:youtube.com", "BBQing");
//		ODao.createInstructor("Aamir", "Sohail", "asohail@gmail.com", "http:youtube.com", "Reading");
//		ODao.createCourse(2, "NodeJS Guide");
		ODao.getCoursesOfAnInstructor(2);
	}

}