package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    * INSERT Student in table Student
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
    * SELECT * FROM student WHERE id = givenId;
    */
    public void selectStudentById(int id) {
        Session session = factory.getCurrentSession();
        try {
            System.out.println("Retrieving Student..... ");
            session.beginTransaction();
            Student result = session.get(Student.class, id);
            System.out.println(result.toString());
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


    /*
    * SELECT * FROM student
    */
    public void selectAllFromStudent() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student", Student.class).getResultList();
            for (Student student : students) {
                System.out.println(student.toString());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


    /*
     * SELECT * FROM student WHERE last_name="Khan";
     */
    public void selectAllFromStudentWhereLastName(String lastName) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student s WHERE s.lastName=:lName", Student.class)
                    .setParameter("lName", lastName)
                    .getResultList();
            for (Student student : students) {
                System.out.println(student.toString());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


    /*
     * SELECT * FROM student WHERE last_name="Khan" AND email LIKE %student.com ;
     */
    public void selectAllFromStudentWhereLastNameAndEmail(String lastName) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> students = session
                    .createQuery("from Student s WHERE s.lastName=:lName OR s.email LIKE '%student.com'", Student.class)
                    .setParameter("lName", lastName)
                    .getResultList();
            for (Student student : students) {
                System.out.println(student.toString());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


    /*
    * UPDATE student SET email = givenEmail WHERE id = givenId
    */
    public void updateEmailSetEmailWhereId(int id, String email) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            student.setEmail(email);
            session.getTransaction().commit();
            System.out.println("Student Updated!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


}
