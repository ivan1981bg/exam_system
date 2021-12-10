package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.service.AnswerServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.repository.AnswerRepository;
import project.exam_system.service.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public AnswerServiceImpl(AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void saveAnswers(List<AnswerServiceModel> answerServiceModels, QuestionServiceModel questionServiceModel) {
        answerServiceModels.stream().
                map(answerServiceModel -> {
                    answerServiceModel.setQuestion(questionServiceModel);
                    Answer answer = modelMapper.map(answerServiceModel, Answer.class);
                    return answer;
                }).
                forEach(answerRepository::save);
    }
}
