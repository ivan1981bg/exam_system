package project.exam_system.service;

import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.ResultServiceModel;
import project.exam_system.model.service.UserServiceModel;

public interface ResultService {
    ResultServiceModel saveResult(String userName, ExamServiceModel examServiceModel, Integer totalCorrect);


}
