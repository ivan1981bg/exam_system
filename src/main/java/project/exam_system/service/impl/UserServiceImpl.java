package project.exam_system.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.exam_system.model.entities.UserEntity;
import project.exam_system.repository.UserRepository;
import project.exam_system.service.UserRoleService;
import project.exam_system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Async
    @Override
    public void seedRootUser() {
        if (userRepository.count() == 0){
            UserEntity user = new UserEntity();
            user.setFirstName("Ivan").
                    setLastName("Panchev").
                    setEmail("i_panchev@abv.bg").
                    setUsername("i_panchev").
                    setPassword(bCryptPasswordEncoder.encode(System.getenv("ROOT_USER_PASSWORD"))).
                    setUserRole(userRoleService.getRolesForRootUser());

           userRepository.save(user);
            System.out.println();
        //    user.setUserRole(userRoleService)
        }

    }
}
