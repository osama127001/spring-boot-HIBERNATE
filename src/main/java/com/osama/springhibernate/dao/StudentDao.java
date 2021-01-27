package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    /*
    * Creating Session Factory
    * Using main/resources/hibernate.cfg.xml config file
    */
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();


    /*
    * Add Student in table Student
    */
    public void createStudent() {
        Session session = factory.getCurrentSession();
        try {
            System.out.println("Creating student object .....");
            Student student = new Student("Awais", "Khan", "awais@student.com");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Student Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    /*
    * SELECT * FROM student WHERE id = givenId
    */
    public void selectStudentById() {
        Session session = factory.getCurrentSession();
        try {
            System.out.println("Retrieving Student..... ");
            session.beginTransaction();
            Student result = session.get(Student.class, 2);
            System.out.println(result.toString());
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}
