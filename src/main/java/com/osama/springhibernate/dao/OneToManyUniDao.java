package com.osama.springhibernate.dao;

import com.osama.springhibernate.model.OneToManyUni.Course;
import com.osama.springhibernate.model.OneToManyUni.Instructor;
import com.osama.springhibernate.model.OneToManyUni.InstructorDetail;
import com.osama.springhibernate.model.OneToManyUni.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OneToManyUniDao {

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
            .buildSessionFactory();


    /*
     * INSERT: Insert course with some reviews.
     */
    public void createCourseAndAddReviews() {
        Session session = factory.getCurrentSession();
        try {
            // Creating Objects
            Course course = new Course("MEAN Course Guide");
            course.addReview(new Review("Good One"));
            course.addReview(new Review("Nice One"));
            course.addReview(new Review("Wow"));

            // Initiating Transaction
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            System.out.println("Course Created and reviews added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    /*
     * GET: Get course and its reviews from the DB.
     */
    public void getCourseAndReviews(int courseId) {
        Session session = factory.getCurrentSession();
        try {
            // Getting Course
            session.beginTransaction();
            Course tempCourse = session.get(Course.class, courseId);
            List<Review> reviews = tempCourse.getReviews();
            System.out.println(tempCourse.getTitle() + " has " + reviews.size() + " reviews: ");
            for (Review r : reviews) {
                System.out.println(r.getComment());
            }
            session.getTransaction().commit();
            System.out.println("Course Created and reviews added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }


}
