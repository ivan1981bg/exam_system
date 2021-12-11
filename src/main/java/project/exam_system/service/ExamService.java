package project.exam_system.service;

import project.exam_system.model.entities.Exam;
import project.exam_system.model.service.ExamServiceModel;

import java.util.List;

public interface ExamService {
    void save(Exam exam);

    List<ExamServiceModel> getAll();
    ExamServiceModel getById(Long id);
}
