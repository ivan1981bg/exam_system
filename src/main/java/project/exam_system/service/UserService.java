package project.exam_system.service;

import project.exam_system.model.service.UserRegisterServiceModel;

public interface UserService {
    void seedRootUser();
    boolean userNameExists(String username);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
}
