package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Log;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.LogServiceModel;
import project.exam_system.model.service.ResultServiceModel;
import project.exam_system.repository.LogRepository;
import project.exam_system.service.ExamService;
import project.exam_system.service.LogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ModelMapper modelMapper;
    private final ExamService examService;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper, ExamService examService) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
        this.examService = examService;
    }


    @Override
    public void createLog(String username, Long examId) {
        ExamServiceModel examServiceModel = examService.getById(examId);

        String description = "Completed exam: " + examServiceModel.getName();

        Log log = new Log()
                .setUsername(username)
                .setDescription(description)
                .setTime(LocalDateTime.now());

        logRepository.save(log);

    }


    @Override
    public LogServiceModel seedLogInDB(LogServiceModel logServiceModel) {
        Log log = this.modelMapper.map(logServiceModel, Log.class);
        return this.modelMapper.map(this.logRepository.saveAndFlush(log),LogServiceModel.class);
    }

    @Override
    public List<LogServiceModel> getAll() {
        return logRepository.findAll().stream()
                .map(log -> modelMapper.map(log, LogServiceModel.class))
                .collect(Collectors.toList());
    }
}
