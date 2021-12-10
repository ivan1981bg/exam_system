package project.exam_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.exam_system.service.QuizService;

@Controller
public class HomeController {

    private final QuizService quizService;

    public HomeController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("exams", quizService.getAll());
        return "home";
    }
}


