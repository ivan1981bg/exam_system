package project.exam_system.service;

import project.exam_system.model.entities.Question;
import project.exam_system.model.service.QuestionServiceModel;

public interface QuestionService {
    QuestionServiceModel save(QuestionServiceModel questionServiceModel);
}
