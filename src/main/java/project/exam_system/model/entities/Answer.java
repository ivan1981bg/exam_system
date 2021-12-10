package project.exam_system.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "answer")
public class Answer extends BaseEntity{
    @Size(min = 1, max = 50, message = "The answer should be less than 50 characters")
    @NotNull(message = "No answer text provided.")
    private String text;

    @ManyToOne
    private Question question;

    @Column(name = "a_order")
    private Integer order;


    public Answer() {
    }

    public String getText() {
        return text;
    }

    public Answer setText(String text) {
        this.text = text;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Answer setOrder(Integer order) {
        this.order = order;
        return this;
    }
}
