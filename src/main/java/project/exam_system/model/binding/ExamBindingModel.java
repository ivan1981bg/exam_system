package project.exam_system.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExamBindingModel {

    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    @NotNull(message = "Please provide a name")
    private String name;

    @Size(max = 255, message = "The description can't be longer than 255 characters.")
    @NotNull(message = "Please, provide a description")
    private String description;


    private String imgUrl;


    public ExamBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ExamBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExamBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ExamBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
