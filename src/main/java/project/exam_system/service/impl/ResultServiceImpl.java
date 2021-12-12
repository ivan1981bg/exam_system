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
    public void saveResult(String userName, ExamServiceModel examServiceModel,int questionIndex, String answer) {
        UserServiceModel userServiceModel = userService.getByUsername(userName);

        ResultServiceModel resultServiceModel = new ResultServiceModel(userServiceModel, examServiceModel, questionIndex, answer);

        resultRepository.save(modelMapper.map(resultServiceModel, Result.class));

    }
}