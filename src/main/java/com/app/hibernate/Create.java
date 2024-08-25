package com.app.hibernate;

import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Create {
    private static final String GREEN = "\033[32m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {
       // Create Configuration and SessionFactory
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        boolean isTransactionSuccessful = false;

        try {
            // Create Session
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Create Student Object
            Student student = new Student();
            student.setName("John Doe");
            student.setCity("San Francisco");

            // Save Student Object
            session.save(student);
            isTransactionSuccessful = true;
        } catch (Exception e) {
            System.out.println(RESET + " Exception occurred. " + e.getMessage());
        } finally {
            if (transaction != null) {
                if (isTransactionSuccessful) {
                    transaction.commit();
                    System.out.println(GREEN + "Transaction committed successfully.");
                } else {
                    transaction.rollback();
                    System.out.println(RESET + "Transaction rolled back.");
                }
            }

            // Close Session and SessionFactory
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
