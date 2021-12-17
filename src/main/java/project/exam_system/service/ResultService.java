package project.exam_system.service;

import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.ResultServiceModel;
import project.exam_system.model.service.UserServiceModel;

import java.util.List;

public interface ResultService {
    ResultServiceModel saveResult(UserServiceModel userServiceModel, ExamServiceModel examServiceModel, Integer totalCorrect);

    List<ResultServiceModel> getAll();
}
