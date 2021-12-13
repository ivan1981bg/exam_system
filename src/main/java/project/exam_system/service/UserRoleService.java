package project.exam_system.service;

import project.exam_system.model.entities.UserRoleEntity;
import project.exam_system.model.entities.enums.UserRole;
import project.exam_system.model.service.UserRoleServiceModel;

import java.util.List;


public interface UserRoleService {
    void seedUserRoles();

    List<UserRoleEntity> getRolesForRootUser();
    UserRoleServiceModel getByUserRole(UserRole role);
}
