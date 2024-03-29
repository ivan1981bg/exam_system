package project.exam_system.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.UserServiceModel;
import project.exam_system.service.ExamService;
import project.exam_system.service.UserService;
import project.exam_system.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    public String index(){

            return "index";

    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public String home(Principal principal, HttpSession session, Model model){

        String userName = principal.getName();
        session.setAttribute("userFirstName", userService.getByUsername(userName).getFirstName());

        List<ExamServiceModel> publishedExams = examService.getAll().
                stream().filter(examServiceModel -> examServiceModel.getPublished())
                        .collect(Collectors.toList());
        model.addAttribute("exams", publishedExams);
        model.addAttribute("userFirstName", userName);
        return "home";
    }

    @GetMapping("/about")
    @PageTitle("About")
    public String about(){

        return "about";

    }
}


