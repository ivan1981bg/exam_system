package project.exam_system.model.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel {

    private String text;
    private List<AnswerViewModel> answers = new ArrayList<>();
    private Integer selectedAnswer;

    public QuestionViewModel() {
    }

    public String getText() {
        return text;
    }

    public QuestionViewModel setText(String text) {
        this.text = text;
        return this;
    }

    public List<AnswerViewModel> getAnswers() {
        return answers;
    }

    public QuestionViewModel setAnswers(List<AnswerViewModel> answers) {
        this.answers = answers;
        return this;
    }

    public Integer getSelectedAnswer() {
        return selectedAnswer;
    }

    public QuestionViewModel setSelectedAnswer(Integer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        return this;
    }
}
