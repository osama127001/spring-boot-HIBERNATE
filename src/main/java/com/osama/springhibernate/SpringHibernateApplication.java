package com.osama.springhibernate;

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

		StudentDao studentDao = new StudentDao();
		// studentDao.createStudent();
		// studentDao.selectStudentById(2);
		studentDao.selectAllFromStudent();
		studentDao.selectAllFromStudentWhereLastName("Khan");
		studentDao.selectAllFromStudentWhereLastNameAndEmail("Khan");


	}

}
