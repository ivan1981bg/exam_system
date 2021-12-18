package project.exam_system.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "users_answers")
public class UserAnswer extends  BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @ManyToOne
    private Question question;

    @Column(name = "answer")
    private String answer;

    public UserAnswer() {
    }

    public UserAnswer(UserEntity user, Question question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserAnswer setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public UserAnswer setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public UserAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
