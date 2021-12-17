package project.exam_system.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.exam_system.error.ObjectNotFoundException;
import project.exam_system.model.entities.Question;
import project.exam_system.model.entities.UserEntity;
import project.exam_system.model.entities.UserRoleEntity;
import project.exam_system.model.entities.enums.UserRole;
import project.exam_system.model.service.*;
import project.exam_system.repository.UserRepository;
import project.exam_system.repository.UserRoleRepository;
import project.exam_system.service.ExamService;
import project.exam_system.service.UserRoleService;
import project.exam_system.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hibernate.cfg.AvailableSettings.USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final ExamSystemUserService examSystemUserService;
    private final ExamService examService;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, UserRoleRepository userRoleRepository, ExamSystemUserService examSystemUserService, ExamService examService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.examSystemUserService = examSystemUserService;
        this.examService = examService;
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
                    setRoles(userRoleService.getRolesForRootUser());

           userRepository.save(user);
            System.out.println();
        //    user.setUserRole(userRoleService)
        }

    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity newUser = modelMapper.map(userRegisterServiceModel, UserEntity.class);
        newUser.setPassword(bCryptPasswordEncoder.encode(userRegisterServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.
                findByRole(UserRole.USER).orElseThrow(
                        () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = examSystemUserService.loadUserByUsername(newUser.getUsername());


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        return userRepository.findByUsername(username).
                map(user -> modelMapper.map(user, UserServiceModel.class)).orElseThrow(null);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void storeUserAnswer(String username, Long examId, Integer questionIndex, String answer) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(null);
        ExamServiceModel examServiceModel = examService.getById(examId);
        QuestionServiceModel questionServiceModel = examServiceModel.getQuestions().get(questionIndex);
        Question question = modelMapper.map(questionServiceModel, Question.class);
        user.setAnswers(new HashMap());
        user.getAnswers().put(question, answer);
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel makeAdmin(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid user identifier!") );

        UserServiceModel userServiceModel = modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getUserRoles().clear();


        userServiceModel.getUserRoles().add(userRoleService.getByUserRole(UserRole.USER));
        userServiceModel.getUserRoles().add(userRoleService.getByUserRole(UserRole.ADMIN));

        return modelMapper.map(userRepository.save(modelMapper.map(userServiceModel, UserEntity.class)), UserServiceModel.class);
    }

    @Override
    public UserServiceModel makeUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid user identifier!"));

        UserServiceModel userServiceModel = modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getUserRoles().clear();

        userServiceModel.getUserRoles().add(userRoleService.getByUserRole(UserRole.USER));

        return modelMapper.map(userRepository.save(modelMapper.map(userServiceModel, UserEntity.class)), UserServiceModel.class);
    }

    @Override
    public UserServiceModel getById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("Invalid user identifier!"));
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid user identifier!"));

        userRepository.delete(user);
    }
}
