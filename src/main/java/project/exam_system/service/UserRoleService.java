package project.exam_system.service;

import project.exam_system.model.entities.UserRoleEntity;

import java.util.List;


public interface UserRoleService {
    void seedUserRoles();

    List<UserRoleEntity> getRolesForRootUser();
}
