package project.exam_system.service.impl;

import org.springframework.stereotype.Service;
import project.exam_system.model.entities.UserRoleEntity;
import project.exam_system.model.entities.enums.UserRole;
import project.exam_system.repository.UserRoleRepository;
import project.exam_system.service.UserRoleService;

import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void seedUserRoles() {
        if (userRoleRepository.count() == 0){
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.ROOT));
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.ADMIN));
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.MODERATOR));
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.USER));
        }
    }

    @Override
    public List<UserRoleEntity> getRolesForRootUser() {
        return userRoleRepository.findAll();
    }
}
