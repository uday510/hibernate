package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class QuestionOneToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToOne(cascade = CascadeType.ALL)
    private AnswerManyToOne answerManyToOne;

    public QuestionOneToOne() {
        System.out.println("Default constructor of Question class");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerManyToOne getAnswer() {
        return answerManyToOne;
    }

    public void setAnswer(AnswerManyToOne answerManyToOne) {
        this.answerManyToOne = answerManyToOne;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer=" + answerManyToOne +
                '}';
    }
}
