package project.exam_system.service;

import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.UserServiceModel;

public interface ResultService {
    void saveResult(String userName, ExamServiceModel examServiceModel,int questionIndex, String answer);


}
