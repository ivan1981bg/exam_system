package project.exam_system.service;

import project.exam_system.model.service.LogServiceModel;

import java.util.List;

public interface LogService {

    void createLog(String action, Long albumId);

    LogServiceModel seedLogInDB(LogServiceModel logServiceModel);

    List<LogServiceModel> getAll();
}
