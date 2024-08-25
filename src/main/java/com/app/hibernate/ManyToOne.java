package com.app.hibernate;

import models.AnswerManyToOne;
import models.QuestionOneToMany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManyToOne {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(QuestionOneToMany.class).addAnnotatedClass(AnswerManyToOne.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        QuestionOneToMany questionOneToMany = new QuestionOneToMany();
        questionOneToMany.setQuestion("What is Java?");

        AnswerManyToOne answerManyToOne = new AnswerManyToOne();
        answerManyToOne.setAnswer("Java is a programming language");
        answerManyToOne.setQuestion(questionOneToMany);

        AnswerManyToOne answerManyToOne1 = new AnswerManyToOne();
        answerManyToOne1.setAnswer("Java is a platform");

        answerManyToOne1.setQuestion(questionOneToMany);
        questionOneToMany.setAnswers(List.of(answerManyToOne, answerManyToOne1));

        Transaction transaction = session.beginTransaction();
        session.save(questionOneToMany);
        session.save(answerManyToOne);
        session.save(answerManyToOne1);
        transaction.commit();

        QuestionOneToMany questionOneToMany1 = session.get(QuestionOneToMany.class, 1L);
        System.out.println(questionOneToMany1.getQuestion());
        for (AnswerManyToOne answer : questionOneToMany1.getAnswers()) {
            System.out.println(answer.getAnswer());
        }

        session.close();
        sessionFactory.close();
        System.out.println("Many to One relationship is working fine");
    }
}
