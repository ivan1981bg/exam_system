package project.exam_system.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class QuestionBindingModel {
    @Size(min = 5, max = 100, message = "The question should be between 5 and 100 characters!")
    private String text;
    private List<String> answersText = new ArrayList<>();
    @NotNull(message = "Please select correct answer!")
    private Integer correctAnswer;

    public QuestionBindingModel() {
    }

    public String getText() {
        return text;
    }

    public QuestionBindingModel setText(String text) {
        this.text = text;
        return this;
    }

    public List<String> getAnswersText() {
        return answersText;
    }

    public QuestionBindingModel setAnswersText(List<String> answersText) {
        this.answersText = answersText;
        return this;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionBindingModel setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
