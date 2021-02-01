package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.instructor_OneToOne.Instructor;
import com.osama.springhibernate.model.instructor_OneToOne.InstructorDetail;
import com.osama.springhibernate.model.student.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorDao {

    /*
     * Creating Session Factory
     * Using main/resources/hibernate.cfg.xml config file
     */
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
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
     * DELETE: Delete an instructor and cascading example
     * By deleting the instructor object, instructor_detail object will also be deleted due to CascadeType.ALL
     */
    public void deleteInstructor(int id) {
        Session session = factory.getCurrentSession();
        try {
            // Getting Instructor
            session.beginTransaction();
            Instructor tempInstructor = session.get(Instructor.class, id);

            // Deleting Instructor
            session.delete(tempInstructor);
            session.getTransaction().commit();
            System.out.println("Instructor Deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    /*
     * GET: Getting Instructor from instructor_detail.
     * Testing bidirectional relationship.
     */
    public void getInstructor(int id) {
        Session session = factory.getCurrentSession();
        try {
            // Getting InstructorDetail
            session.beginTransaction();
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("InstructorDetail: " + tempInstructorDetail);

            // Getting Instructor from InstructorDetail
            Instructor tempInstructor = tempInstructorDetail.getInstructor();
            System.out.println("Instructor form InstructorDetail: " + tempInstructor);
            session.getTransaction().commit();
            System.out.println("Instructor Displayed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}
