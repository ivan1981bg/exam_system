package project.exam_system.web.filters;


import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.service.AuthenticatedUserService;
import project.exam_system.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Set;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticatedUserService authenticatedUserService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public UserAuthenticationSuccessHandler(AuthenticatedUserService authenticatedUserService,
                                            UserService userService,  ModelMapper modelMapper) {
        this.authenticatedUserService = authenticatedUserService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        org.springframework.security.core.Authentication authentication)
                                        throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin/panel");
        }

        /*
        String username = authenticatedUserService.getUsername();

        UserServiceModel user = userService.getByUsername(username);

        if (user == null) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/users/login");
        }

        if (doctor == null) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
        } else {
            if (!doctorService.isAccountCompleted(modelMapper.map(doctor, DoctorServiceModel.class))) {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/doctor/complete");
            } else {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/doctor/dashboard");
            }
        }*/
    }
}
