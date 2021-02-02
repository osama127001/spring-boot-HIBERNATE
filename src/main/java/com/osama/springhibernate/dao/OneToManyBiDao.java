package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.OneToManyBi.Course;
import com.osama.springhibernate.model.OneToManyBi.Instructor;
import com.osama.springhibernate.model.OneToManyBi.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OneToManyBiDao {

    /*
     * Creating Session Factory
     * Using main/resources/hibernate.cfg.xml config file
     */
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();


    /*
     * INSERT: Inserting in instructor and instructor_detail.
     */
    public void createInstructor(String firstName, String lastName, String email, String ytChannel, String hobby) {
        Session session = factory.getCurrentSession();
        try {
            // Creating Objects
            Instructor tempInstructor = new Instructor(firstName, lastName, email);
            InstructorDetail tempInstructorDetail = new InstructorDetail(ytChannel, hobby);
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // Initiating Transaction
            session.beginTransaction();
            session.save(tempInstructor);
            session.getTransaction().commit();
            System.out.println("Instructor Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


    /*
    * INSERT: Create and insert courses in instructor and tables
    */
    public void createCourse(int id, String courseName) {
        Session session = factory.getCurrentSession();
        try {
            // Creating Courses
            Course tempCourse1 = new Course(courseName);
            Course tempCourse2 = new Course("MEAN Stack Guide");

            // Initiating Transaction
            session.beginTransaction();
            Instructor tempInstructor = session.get(Instructor.class, id);
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.getTransaction().commit();
            System.out.println("Instructor Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    /*
     * INSERT: Inserting in instructor and instructor_detail.
     */
    public void getCoursesOfAnInstructor(int instructorId) {
        Session session = factory.getCurrentSession();
        try {
            // Initiating Transaction
            session.beginTransaction();
            Instructor tempInstructor = session.get(Instructor.class, instructorId);
            System.out.println(tempInstructor.getFirstName() + tempInstructor.getLastName() + "has courses");
            List<Course> courses = tempInstructor.getCourses();
            for (Course c : courses) {
                System.out.println(c.getTitle());
            }
            session.getTransaction().commit();
            System.out.println("Instructor retrieved!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}
