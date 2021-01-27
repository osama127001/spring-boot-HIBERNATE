package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    *retrieving current session
    */
    Session session = factory.getCurrentSession();

    /*
    * Add Student in table Student
    */
    public void createStudent() {
        try {
            System.out.println("Creating student object .....");
            Student student = new Student("Aamir", "Hanif", "aamir@student.com");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Student Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            factory.close();
        }
    }

}
