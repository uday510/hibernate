package com.app.hibernate;

import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Update {
    public static void main(String[] args) {
        boolean isTransactionSuccessful = false;
        Transaction transaction = null;
      try ( SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory()) {
             Session session = sessionFactory.openSession();
             transaction = session.beginTransaction();
             Student student = new Student();
             student.setName("Alan Turing");
             student.setCity("London");

             session.persist(student);
             isTransactionSuccessful = true;
      } catch (Exception e) {
          System.out.println("Exception occurred. " + e);
      } finally {
          if (isTransactionSuccessful) {
              transaction.commit();
              System.out.println("Record updated successfully");
          } else {
              assert transaction != null;
              transaction.rollback();
              System.out.println("Record update failed");
          }
      }
    }
}
