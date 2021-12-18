package project.exam_system.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.Result;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.ResultServiceModel;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.repository.ResultRepository;
import project.exam_system.service.ResultService;
import project.exam_system.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ResultRepository resultRepository;

    public ResultServiceImpl(UserService userService, ModelMapper modelMapper, ResultRepository resultRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.resultRepository = resultRepository;
    }

    @Override
    public ResultServiceModel saveResult(UserServiceModel userServiceModel, ExamServiceModel examServiceModel,Integer totalCorrect) {
        StringBuilder fullName = new StringBuilder().append(userServiceModel.getFirstName()).
        append(" ").
        append(userServiceModel.getLastName());

        ResultServiceModel resultServiceModel = new ResultServiceModel(fullName.toString(), examServiceModel,
                totalCorrect, examServiceModel.getQuestions().size());

        Result result = resultRepository.save(modelMapper.map(resultServiceModel, Result.class));
        return modelMapper.map(result, ResultServiceModel.class);
    }

    @Override
    public List<ResultServiceModel> getAll() {
        return resultRepository.findAll().stream()
                .map(user -> modelMapper.map(user, ResultServiceModel.class))
                .collect(Collectors.toList());
    }
}
