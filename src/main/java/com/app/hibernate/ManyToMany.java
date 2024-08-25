package com.app.hibernate;

import models.Course;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class ManyToMany {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean flag = false;

        try {
            transaction = session.beginTransaction();

            Course course1 = new Course();
            course1.setCourseName("Java");
            course1.setCoursePrice("100");

            Course course2 = new Course();
            course2.setCourseName("Python");
            course2.setCoursePrice("120");

            Set<Course> courses = new HashSet<>();
            courses.add(course1);
            courses.add(course2);

            Student student1 = new Student();
            student1.setName("John");
            student1.setCity("New York");
            student1.setCourses(courses);

            Student student2 = new Student();
            student2.setName("Smith");
            student2.setCity("Los Angeles");
            student2.setCourses(courses);

            Student student3 = new Student();
            student3.setName("Williams");
            student3.setCity("Chicago");
            student3.setCourses(courses);

            session.persist(student1);
            session.persist(student2);
            session.persist(student3);

            flag = true;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (flag) {
                transaction.commit();
                System.out.println("Student records inserted successfully.");
            } else {
                System.out.println("Student records failed to insert.");
            }
            session.close();
            sessionFactory.close();
        }
    }
}
