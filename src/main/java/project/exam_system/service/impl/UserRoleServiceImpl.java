package project.exam_system.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.UserRoleEntity;
import project.exam_system.model.entities.enums.UserRole;
import project.exam_system.model.service.UserRoleServiceModel;
import project.exam_system.repository.UserRoleRepository;
import project.exam_system.service.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;


    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUserRoles() {
        if (userRoleRepository.count() == 0){
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.ROOT));
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.ADMIN));
            userRoleRepository.save(new UserRoleEntity().setRole(UserRole.USER));
        }
    }

    @Override
    public List<UserRoleEntity> getRolesForRootUser() {

        return userRoleRepository.findAll();
    }

    @Override
    public UserRoleServiceModel getByUserRole(UserRole role) {
        return userRoleRepository.findByRole(role)
                .map(userRole -> modelMapper.map(userRole, UserRoleServiceModel.class))
                .orElseThrow(null);
    }
}
