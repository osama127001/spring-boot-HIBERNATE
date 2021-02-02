package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.ManyToMany.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyDao {


    /*
     * Creating Session Factory
     * Using main/resources/hibernate.cfg.xml config file
     */
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();


    /*
     * INSERT: Create course and add students in the course.
     */
    public void crateCourseAndAddStudents() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // Creating Course
            Course course = new Course("MEAN Stack Guide");

            // saving course
            session.save(course);

            // Creating Students
            Student student1 = new Student("Osama", "Khan", "osama@gmail.com");
            Student student2 = new Student("aamir", "hanif", "aamir@gmail.com");

            // Adding Students
            course.addStudent(student1);
            course.addStudent(student2);

            // Saving students
            session.save(student1);
            session.save(student2);
            session.getTransaction().commit();
            System.out.println("Saved courses and students!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}
