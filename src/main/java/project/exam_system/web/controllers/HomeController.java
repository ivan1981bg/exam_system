package project.exam_system.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.exam_system.service.ExamService;
import project.exam_system.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ExamService examService;

    public HomeController(ExamService examService) {
        this.examService = examService;
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
    public String home(Model model){

        model.addAttribute("exams", examService.getAll());
        return "home";
    }
}


