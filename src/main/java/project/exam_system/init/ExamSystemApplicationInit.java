package project.exam_system.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.exam_system.service.UserRoleService;
import project.exam_system.service.UserService;

@Component
public class ExamSystemApplicationInit implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final UserService userService;

    public ExamSystemApplicationInit(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        userRoleService.seedUserRoles();
        userService.seedRootUser();
    }
}
