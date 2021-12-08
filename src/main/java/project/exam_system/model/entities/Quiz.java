package project.exam_system.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizes")
public class Quiz extends BaseEntity{

    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    @NotNull(message = "Please provide a name")
    private String name;

    @Size(max = 500, message = "The description can't be longer than 500 characters.")
    @NotNull(message = "Please, provide a description")
    private String description;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    private Boolean isPublished = false;

    private Integer numberOfQuestions;


    public Quiz() {
    }

    public String getName() {
        return name;
    }

    public Quiz setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Quiz setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Quiz setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public Quiz setPublished(Boolean published) {
        isPublished = published;
        return this;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Quiz setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        return this;
    }
}
