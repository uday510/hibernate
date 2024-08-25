package com.app.hibernate;

import models.Employee1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TransientAnnotation {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Employee1.class).buildSessionFactory();

        Session session = null;
        Transaction transaction = null;

        boolean flag = false;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Employee1 employee = new Employee1();
            employee.setId(1);
            employee.setName("John Doe");
            employee.setCity("New York");

            session.persist(employee);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                if (flag) {
                    transaction.commit();
                } else {
                    transaction.rollback();
                }
            }
            assert session != null;
            session.close();
            sessionFactory.close();
        }
    }
}
