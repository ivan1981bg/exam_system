package project.exam_system.model.service;

import project.exam_system.model.entities.Question;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class QuizServiceModel {
    private Long id;
    private String name;
    private String description;
    private List<QuestionServiceModel> questions;// = new ArrayList<>();
    private Boolean isPublished = false;
    private Integer numberOfQuestions;

    public QuizServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public QuizServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuizServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuizServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<QuestionServiceModel> getQuestions() {
        return questions;
    }

    public QuizServiceModel setQuestions(List<QuestionServiceModel> questions) {
        this.questions = questions;
        return this;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public QuizServiceModel setPublished(Boolean published) {
        isPublished = published;
        return this;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public QuizServiceModel setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }
}
