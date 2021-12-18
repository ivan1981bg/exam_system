package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.entities.UserAnswer;
import project.exam_system.model.entities.UserEntity;
import project.exam_system.model.service.AnswerServiceModel;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.repository.AnswerRepository;
import project.exam_system.repository.ExamRepository;
import project.exam_system.repository.UserAnswerRepository;
import project.exam_system.repository.UserRepository;
import project.exam_system.service.AnswerService;
import project.exam_system.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;
    private final ExamRepository examRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, ModelMapper modelMapper, ExamRepository examRepository, UserAnswerRepository userAnswerRepository, UserService userService, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
        this.examRepository = examRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userService = userService;
        this.userRepository = userRepository;
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
                    return modelMapper.map(answerServiceModel, Answer.class);
                }).
                forEach(answerRepository::saveAndFlush);
    }

    @Override
    public Integer getTotalCorrect(UserServiceModel userServiceModel, ExamServiceModel examServiceModel) {

        List<Answer> correctAnswers = examRepository.getCorrectAnswers(examServiceModel.getId());



        List<String> user_answers = examServiceModel.getQuestions().stream()
                .map(question -> userAnswerRepository.getByUserIdAndQuestionId(userServiceModel.getId(), question.getId()))
                .map(userAnswer -> userAnswer.getAnswer())
        .toList();

        List<String> matchList = correctAnswers.stream()
                .map(Answer::getText).
                filter(user_answers::contains).toList();
        return matchList.size();
    }

    @Override
    @Transactional
    public void deleteAnswersQuestion(Long qId) {
        answerRepository.deleteByQuestionId(qId);
    }
}
