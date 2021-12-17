package project.exam_system.model.view;

public class ResultsAllViewModel {

    private String fullName;
    private String examName;
    private Integer totalCorrect;
    private Integer numberOfQuestions;

    public ResultsAllViewModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public ResultsAllViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getExamName() {
        return examName;
    }

    public ResultsAllViewModel setExamName(String examName) {
        this.examName = examName;
        return this;
    }

    public Integer getTotalCorrect() {
        return totalCorrect;
    }

    public ResultsAllViewModel setTotalCorrect(Integer totalCorrect) {
        this.totalCorrect = totalCorrect;
        return this;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public ResultsAllViewModel setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }
}
