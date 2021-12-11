package project.exam_system.model.service;

import project.exam_system.model.entities.Exam;
import project.exam_system.model.entities.UserEntity;

import javax.persistence.ManyToOne;

public class ResultServiceModel {
    private UserServiceModel user;

    private ExamServiceModel exam;

    private Integer questionOrder;

    private String selectedAnswer;

    public ResultServiceModel() {
    }

    public ResultServiceModel(UserServiceModel user, ExamServiceModel exam, Integer questionOrder, String selectedAnswer) {
        this.user = user;
        this.exam = exam;
        this.questionOrder = questionOrder;
        this.selectedAnswer = selectedAnswer;
    }

    public UserServiceModel getUser() {
        return user;
    }


    public ResultServiceModel setUser(UserServiceModel user) {
        this.user = user;
        return this;
    }

    public ExamServiceModel getExam() {
        return exam;
    }

    public ResultServiceModel setExam(ExamServiceModel exam) {
        this.exam = exam;
        return this;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public ResultServiceModel setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
        return this;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public ResultServiceModel setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        return this;
    }
}
