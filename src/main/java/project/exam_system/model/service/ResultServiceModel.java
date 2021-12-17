package project.exam_system.model.service;

import project.exam_system.model.entities.Exam;
import project.exam_system.model.entities.UserEntity;

import javax.persistence.ManyToOne;

public class ResultServiceModel {
    private String fullName;

    private ExamServiceModel exam;

    private Integer totalCorrect;
    private Integer numberOfQuestions;


    public ResultServiceModel() {
    }

    public ResultServiceModel(String fullName, ExamServiceModel exam, Integer totalCorrect, Integer numberOfQuestions) {
        this.fullName = fullName;
        this.exam = exam;
        this.totalCorrect = totalCorrect;
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getFullName() {
        return fullName;
    }

    public ResultServiceModel setFullName(String fullName) {
        this.fullName = fullName;
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

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public ResultServiceModel setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }


}
