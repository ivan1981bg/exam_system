package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.binding.ExamBindingModel;
import project.exam_system.model.binding.UserRegisterBindingModel;
import project.exam_system.model.entities.Exam;
import project.exam_system.model.view.UsersAllViewModel;
import project.exam_system.service.ExamService;
import project.exam_system.service.UserService;
import project.exam_system.web.annotations.PageTitle;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ExamService examService;

    public AdminController(ModelMapper modelMapper, UserService userService, ExamService examService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.examService = examService;
    }

    @ModelAttribute("examBindingModel")
    public ExamBindingModel createBindingModel() {
        return new ExamBindingModel();
    }

    @GetMapping("/panel")
    @PageTitle("Admin panel")
    public String admin() {
        return "admin_dashboard";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ModelAndView getAllView(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAll()
                .stream()
                .map(userServiceModel -> modelMapper.map(userServiceModel, UsersAllViewModel.class))
                .collect(Collectors.toList()));

        modelAndView.setViewName("usersInfo");
        return modelAndView;
    }


    @GetMapping("/create-new")
    public String newExam() {


        return "new-exam";
    }


    @PostMapping("/create-new")
    public String addQuestion(@Valid ExamBindingModel examBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("examBindingModel", examBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.examBindingModel", bindingResult);
            return "redirect:create-new";
        }
            examService.save(modelMapper.map(examBindingModel, Exam.class));

        return "redirect:panel";
    }
}
