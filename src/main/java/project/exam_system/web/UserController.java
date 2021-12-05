package project.exam_system.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.binding.UserRegisterBindingModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;


    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {

        return "register";
    }


    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegisterBindingModel registerBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
/*
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.userNameExists(registrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        UserRegistrationServiceModel userServiceModel = modelMapper
                .map(registrationBindingModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(userServiceModel);
*/
        return "redirect:/home";
    }
}
