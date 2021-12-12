package project.exam_system.model.entities;

import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Result extends BaseEntity{

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Exam exam;

    private Integer questionOrder;

    private String selectedAnswer;

    public Result() {
    }

    public UserEntity getUser() {
        return user;
    }

    public Result setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Exam getExam() {
        return exam;
    }

    public Result setExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public Result setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
        return this;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public Result setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        return this;
    }


}