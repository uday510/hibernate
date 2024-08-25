package com.app.hibernate;

import models.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
    public static void main(String[] args) {
        // Read operation
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session1 = null;
        Session session2 = null;
        try {
            session1 = sessionFactory.openSession();

            Student student = session1.get(Student.class, 1L);
            System.out.println(student);
//            session2 = sessionFactory.openSession();
//            Student student1 = session2.get(Student.class, 1L);
//            System.out.println(student1);
        } catch (HibernateException e) {
            System.out.println("Exception occurred. " + e);
        } finally {
            session1.close();
            sessionFactory.close();
        }

    }
}
