package project.exam_system.service.impl;

import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Quiz;
import project.exam_system.repository.QuizRepository;
import project.exam_system.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }
}
