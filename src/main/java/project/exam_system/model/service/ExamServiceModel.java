package project.exam_system.model.service;


import java.util.List;

public class ExamServiceModel {
    private Long id;
    private String name;
    private String description;
    private List<QuestionServiceModel> questions;// = new ArrayList<>();
    private Boolean isPublished = false;
    private Integer numberOfQuestions;

    public ExamServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public ExamServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExamServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExamServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<QuestionServiceModel> getQuestions() {
        return questions;
    }

    public ExamServiceModel setQuestions(List<QuestionServiceModel> questions) {
        this.questions = questions;
        return this;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public ExamServiceModel setPublished(Boolean published) {
        isPublished = published;
        return this;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public ExamServiceModel setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }
}
