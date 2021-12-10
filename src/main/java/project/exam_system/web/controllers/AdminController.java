package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.entities.Quiz;
import project.exam_system.model.view.UsersAllViewModel;
import project.exam_system.service.QuizService;
import project.exam_system.service.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final QuizService quizService;

    public AdminController(ModelMapper modelMapper, UserService userService, QuizService quizService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.quizService = quizService;
    }

    @GetMapping("/panel")
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

        //modelAndView.setViewName("all-users");
        modelAndView.setViewName("usersInfo");
        return modelAndView;
    }

    @ModelAttribute("quiz")
    public Quiz createQuiz() {
        return new Quiz();
    }


    @GetMapping("/create-new")
    public String newQuestion(@ModelAttribute Quiz quiz,
                              @RequestParam(name = "numberOfQuestions", defaultValue = "1") int numberOfQuestions,
                              ModelMap model) {

        model.addAttribute("numberOfQuestions", numberOfQuestions);
        model.addAttribute("MAX_QUESTIONS", 20);
        return "new-exam";
    }


    @PostMapping("/create-new")
    public String addQuestion(@Valid Quiz quiz, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("numberOfQuestions", quiz.getQuestions().size());
            model.addAttribute("MAX_QUESTIONS", 20);
            return "new-exam";
        }


            quizService.save(quiz);

        return "redirect:panel";
    }
}
