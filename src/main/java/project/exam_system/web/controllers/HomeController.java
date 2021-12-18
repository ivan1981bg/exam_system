package project.exam_system.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.service.ExamService;
import project.exam_system.service.UserService;
import project.exam_system.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    private final ExamService examService;
    private final UserService userService;

    public HomeController(ExamService examService, UserService userService) {
        this.examService = examService;
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public String index(HttpSession httpSession){


        return "index";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public String home(Principal principal, HttpSession session, Model model){

        String userName = principal.getName();
        UserServiceModel userServiceModel = userService.getByUsername(userName);
        session.setAttribute("userFirstName", userServiceModel.getFirstName());

        model.addAttribute("exams", examService.getAll());
        model.addAttribute("userFirstName", userName);
        return "home";
    }
}


