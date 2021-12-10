package project.exam_system.model.service;

import project.exam_system.model.entities.Question;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class AnswerServiceModel {


    private String text;
    private QuestionServiceModel question;
    private Integer order;

    public AnswerServiceModel() {
    }

    public AnswerServiceModel(String text, Integer order) {
        this.text = text;
        this.order = order;
    }

    public AnswerServiceModel(String text, QuestionServiceModel question, Integer order) {
        this.text = text;
        this.question = question;
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public AnswerServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public QuestionServiceModel getQuestion() {
        return question;
    }

    public AnswerServiceModel setQuestion(QuestionServiceModel question) {
        this.question = question;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public AnswerServiceModel setOrder(Integer order) {
        this.order = order;
        return this;
    }
}
