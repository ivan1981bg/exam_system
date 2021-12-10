package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Quiz;
import project.exam_system.model.service.QuizServiceModel;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.repository.QuizRepository;
import project.exam_system.service.QuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<QuizServiceModel> getAll() {

        return quizRepository.findAll().stream()
                .map(user -> modelMapper.map(user, QuizServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizServiceModel getById(Long id) {
        return quizRepository.findById(id).
                map(quiz -> modelMapper.map(quiz, QuizServiceModel.class)).
                orElse(null);
    }
}
