package project.exam_system.service;

import project.exam_system.model.service.ExamServiceModel;

public interface ResultService {
    void saveResult(String userName, ExamServiceModel examServiceModel,int questionIndex, String answer);
}
