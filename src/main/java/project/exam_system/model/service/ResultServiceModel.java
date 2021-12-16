package project.exam_system.model.service;

import project.exam_system.model.entities.Exam;
import project.exam_system.model.entities.UserEntity;

import javax.persistence.ManyToOne;

public class ResultServiceModel {
    private UserServiceModel user;

    private ExamServiceModel exam;

    private Integer totalCorrect;



    public ResultServiceModel() {
    }

    public ResultServiceModel(UserServiceModel user, ExamServiceModel exam, Integer totalCorrect) {
        this.user = user;
        this.exam = exam;
        this.totalCorrect = totalCorrect;
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

    public Integer getTotalCorrect() {
        return totalCorrect;
    }

    public ResultServiceModel setTotalCorrect(Integer totalCorrect) {
        this.totalCorrect = totalCorrect;
        return this;
    }
}
