package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.binding.UserRegisterBindingModel;
import project.exam_system.model.service.UserRegisterServiceModel;
import project.exam_system.service.UserService;
import project.exam_system.web.annotations.PageTitle;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @ModelAttribute("bad_credentials")
    public boolean badCredentials() {
        return false;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("userAlreadyExists")
    public boolean userAlreadyExists() {
        return false;
    }

    @GetMapping("/login")
    @PageTitle("Login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                            String username, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        modelAndView.setViewName("redirect:/users/login");
        return modelAndView;
    }


    @GetMapping("/register")
    @PageTitle("Register")
    public String register() {

        return "register";
    }


    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        if (userService.userNameExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);

            return "redirect:register";
        }

        UserRegisterServiceModel userRegisterServiceModel = modelMapper
                .map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(userRegisterServiceModel);

        return "redirect:/home";
    }


    @PostMapping("/set-admin/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdminRole(@PathVariable Long userId, ModelAndView modelAndView) {
        System.out.println();

        userService.makeAdmin(userId);
        modelAndView.setViewName("redirect:/admin/all-users");
        return modelAndView;
    }

    @PostMapping("/set-user/{userId}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView setUserRole(@PathVariable Long userId, ModelAndView modelAndView) {
        userService.makeUser(userId);
        modelAndView.setViewName("redirect:/admin/all-users");
        return modelAndView;
    }




    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Delete user")
    public ModelAndView deleteUserConfirm(@PathVariable Long id, ModelAndView modelAndView) {
        userService.deleteUserAnswers(id);
        userService.deleteUser(id);
        modelAndView.setViewName("redirect:/admin/all-users");
        return modelAndView;
    }

}
