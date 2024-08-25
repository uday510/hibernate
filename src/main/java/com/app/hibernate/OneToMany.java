package com.app.hibernate;

import models.Answer;
import models.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class OneToMany {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Answer.class);

        boolean flag = false;

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            // Begin transaction
            Transaction transaction = session.beginTransaction();

            try {
                Question question = new Question();
                question.setQuestion("What is Programming?");

                Answer answerManyToOne1 = new Answer();
                answerManyToOne1.setAnswer("Programming is the process of creating a set of instructions that tell a computer how to perform a task");

                Answer answerManyToOne2 = new Answer();
                answerManyToOne2.setAnswer("Programming is the process of creating software applications");

                ArrayList<Answer> answerManyToOnes = new ArrayList<>();
                answerManyToOnes.add(answerManyToOne1);
                answerManyToOnes.add(answerManyToOne2);

                question.setAnswers(answerManyToOnes);

                session.persist(question);
                flag = true;
            } catch (Exception exception) {
                System.out.printf("""
                        There was an error while opening the session or building the session factory.
                        Error: %s
                        %n""", exception.getMessage());
            } finally {
                if (flag) {
                    transaction.commit();
                    System.out.println("Data saved successfully");
                } else {
                    transaction.rollback();
                    System.out.println("There was an error while saving the data");
                }
            }
        }
    }
}
