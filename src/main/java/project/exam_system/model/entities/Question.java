package project.exam_system.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="questions")
public class Question extends BaseEntity{

    @Size(min = 2, max = 150, message = "The question should be between 2 and 150 characters")
    @NotNull(message = "Question text not provided")
    private String text;

    @ManyToOne
    private Quiz quiz;

    @Column(name = "q_order")
    private Integer order;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

    @OneToOne
    private Answer correctAnswer;


    public Question() {
    }

    public String getText() {
        return text;
    }

    public Question setText(String text) {
        this.text = text;
        return this;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Question setQuiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Question setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question setAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public Question setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
