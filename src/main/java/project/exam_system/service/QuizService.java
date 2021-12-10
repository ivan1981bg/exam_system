package project.exam_system.service;

import project.exam_system.model.entities.Quiz;
import project.exam_system.model.service.QuizServiceModel;
import project.exam_system.model.service.UserServiceModel;

import java.util.List;

public interface QuizService {
    void save(Quiz quiz);

    List<QuizServiceModel> getAll();
    QuizServiceModel getById(Long id);
}
