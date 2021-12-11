package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Question;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.repository.QuestionRepository;
import project.exam_system.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionServiceModel save(QuestionServiceModel questionServiceModel) {
        Question question = modelMapper.map(questionServiceModel, Question.class);

        question = questionRepository.saveAndFlush(question);

        questionServiceModel = modelMapper.map(question, QuestionServiceModel.class);
        return questionServiceModel;

    }

    @Override
    public QuestionServiceModel getQuestionByOrder(Integer order) {

        return modelMapper.map(questionRepository.getQuestionByOrder(order), QuestionServiceModel.class);
    }
}
