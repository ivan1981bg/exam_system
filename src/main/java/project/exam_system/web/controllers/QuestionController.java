package project.exam_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.service.QuizService;

import javax.validation.Valid;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuizService quizService;

    public QuestionController(QuizService quizService) {
        this.quizService = quizService;
    }


    @ModelAttribute("question")
    public QuestionServiceModel createQuestion() {
        return new QuestionServiceModel();
    }



    @PostMapping("/new")
    public String addQuestion(@Valid QuestionServiceModel question, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttrs) {

        System.out.println();

        System.out.println();
        /*
        if (bindingResult.hasErrors()) {
            model.addAttribute("numberOfAnswers", question.getAnswers().size());
            model.addAttribute("MAX_ANSWERS", 5);
            return "newQuestion";
        }
        if (questionService.exist(question)) {
            redirectAttrs.addAttribute("numberOfAnswers", question.getAnswers().size());
            redirectAttrs.addFlashAttribute("message", "A question like this already exist.");
            redirectAttrs.addFlashAttribute(question);
            return"redirect:/questions/new";
        }
        try {
            questionService.save(question);
        } catch (Exception e) {
            redirectAttrs.addAttribute("numberOfAnswers", question.getAnswers().size());
            redirectAttrs.addFlashAttribute("message", "There was an error while adding the question.");
            redirectAttrs.addFlashAttribute(question);
            return"redirect:/questions/new";
        }*/
        return "redirect:/questions/byMe";
    }




}
