package models;

import jakarta.persistence.*;

@Entity
public class AnswerManyToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    private QuestionOneToMany question;

    public AnswerManyToOne() {
        System.out.println("AnswerManyToOne Default Constructor");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionOneToMany getQuestion() {
        return question;
    }

    public void setQuestion(QuestionOneToMany question) {
        this.question = question;
    }
}
