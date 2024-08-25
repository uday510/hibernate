package com.app.hibernate;

import models.Answer;
import models.QuestionOneToOne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToOne {
    public static void main(String[] args) {
        // Initialize Configuration and specify the hibernate.cfg.xml file
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(QuestionOneToOne.class)
                .addAnnotatedClass(Answer.class);

        boolean flag = false;

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            // Begin transaction
            Transaction transaction = session.beginTransaction();

            try {
                // Create Question and Answer objects
//                Question question1 = new Question();
//                question1.setQuestion("What is Java?");
//
//                Answer answer1 = new Answer();
//                answer1.setAnswer("Java is a programming language");
//
//                question1.setAnswer(answer1);

                // Save the question, which should cascade and save the answer
//                session.persist(question1);

                QuestionOneToOne result = session.get(QuestionOneToOne.class, 1);

                System.out.println(result);

                flag = true;
            } catch (HibernateException exception) {
                System.out.printf("""
                    There was an error while saving the data.
                    Error: %s
                    %n""", exception.getMessage());
                transaction.rollback();
            }

            if (flag) {
                transaction.commit();
//                System.out.println("Data saved successfully");
            }

        } catch (HibernateException exception) {
            System.out.printf("""
                    There was an error while opening the session or building the session factory.
                    Error: %s
                    %n""", exception.getMessage());
        }
    }
}
