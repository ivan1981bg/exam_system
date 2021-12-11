package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Exam;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.repository.ExamRepository;
import project.exam_system.service.ExamService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;

    public ExamServiceImpl(ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public List<ExamServiceModel> getAll() {

        return examRepository.findAll().stream()
                .map(user -> modelMapper.map(user, ExamServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExamServiceModel getById(Long id) {
        return examRepository.findById(id).
                map(exam -> modelMapper.map(exam, ExamServiceModel.class)).
                orElse(null);
    }
}
