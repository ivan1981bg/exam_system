package project.exam_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.exam_system.service.ExamService;

@Controller
public class HomeController {

    private final ExamService examService;

    public HomeController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("exams", examService.getAll());
        return "home";
    }
}


