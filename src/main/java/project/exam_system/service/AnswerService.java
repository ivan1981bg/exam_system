package project.exam_system.service;

import project.exam_system.model.entities.Answer;
import project.exam_system.model.service.AnswerServiceModel;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.model.service.UserServiceModel;

import java.util.List;

public interface AnswerService {

    void save(Answer answer);

    void saveAnswers(List<AnswerServiceModel> answerServiceModels, QuestionServiceModel questionServiceModel);

    Integer getTotalCorrect(UserServiceModel userServiceModel, ExamServiceModel examId);

    void deleteAnswersQuestion(Long qId);
}
