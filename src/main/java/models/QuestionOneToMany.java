package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class QuestionOneToMany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerManyToOne> answers;

    public QuestionOneToMany() {
        System.out.println("Default constructor of QuestionOneToMany class");
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

    public List<AnswerManyToOne> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerManyToOne> answers) {
        this.answers = answers;
    }
}
