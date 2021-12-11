package project.exam_system.model.view;

import project.exam_system.model.entities.Question;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerViewModel {
    private String text;

    public AnswerViewModel() {
    }

    public String getText() {
        return text;
    }

    public AnswerViewModel setText(String text) {
        this.text = text;
        return this;
    }
}
