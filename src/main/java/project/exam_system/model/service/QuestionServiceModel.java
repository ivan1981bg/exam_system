package project.exam_system.model.service;


import java.util.List;

public class QuestionServiceModel {

    private Long id;
    private String text;
    private QuizServiceModel quiz;
    private Integer order;
    private List<AnswerServiceModel> answers;
    private Integer correctAnswer;


    public QuestionServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public QuestionServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public QuestionServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public QuizServiceModel getQuiz() {
        return quiz;
    }

    public QuestionServiceModel setQuiz(QuizServiceModel quiz) {
        this.quiz = quiz;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public QuestionServiceModel setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public List<AnswerServiceModel> getAnswers() {
        return answers;
    }

    public QuestionServiceModel setAnswers(List<AnswerServiceModel> answers) {
        this.answers = answers;
        return this;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionServiceModel setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
