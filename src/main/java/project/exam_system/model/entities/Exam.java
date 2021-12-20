package project.exam_system.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam extends BaseEntity{

    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    @NotNull(message = "Please provide a name")
    private String name;

    @Size(max = 255, message = "The description can't be longer than 255 characters.")
    @NotNull(message = "Please, provide a description")
    private String description;


    private String imgUrl;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    private Boolean isPublished = false;

    public Exam() {
    }

    public String getName() {
        return name;
    }

    public Exam setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Exam setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Exam setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public Exam setPublished(Boolean published) {
        isPublished = published;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Exam setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
