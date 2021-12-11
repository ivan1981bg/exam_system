package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.model.view.QuestionViewModel;
import project.exam_system.service.QuestionService;
import project.exam_system.service.ExamService;
import project.exam_system.service.ResultService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final ExamService examService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    private final ResultService resultService;

    public QuestionController(ExamService examService, QuestionService questionService, ModelMapper modelMapper, ResultService resultService) {
        this.examService = examService;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
        this.resultService = resultService;
    }


    @ModelAttribute("question")
    public QuestionServiceModel createQuestion() {
        return new QuestionServiceModel();
    }


    @GetMapping("/show")
    public String showQuestion(@RequestParam(name = "e") Long examId,
                               @RequestParam(name = "q") Integer questionIndex,
                               Model model) {

        ExamServiceModel examServiceModel = examService.getById(examId);
        int numberOfQuestions = examServiceModel.getNumberOfQuestions();
        List<QuestionServiceModel> questions = examServiceModel.getQuestions();
        if (questionIndex < numberOfQuestions) {

            QuestionViewModel questionViewModel = modelMapper.
                    map(questions.get(questionIndex), QuestionViewModel.class);
            model.addAttribute("questionViewModel", questionViewModel);
            model.addAttribute("questionIndex", questionIndex);
            model.addAttribute("numberOfQuestions", numberOfQuestions);
            model.addAttribute("examId", examId);
            return "show-question";
        }
        else {
            return "redirect:/quiz/completed";
        }
    }

    @PostMapping("/show")
    public String userAnswered( Principal principal,
                        //        @RequestParam(name = "qId") Long questionId,
                                @RequestParam(name = "e") Long examId,
                                @RequestParam(defaultValue = "") String selectedAnswer,
                                @RequestParam(name = "q") int questionIndex,
                                RedirectAttributes redirectAttributes) {

        if (selectedAnswer.isBlank()) {
            redirectAttributes.addFlashAttribute("message", "Please select an answer before clicking the submit button.");
            return "redirect:show?e=" + examId + "&q=" + questionIndex;
        }

        String username = principal.getName();
        resultService.saveResult(username, examService.getById(examId), questionIndex, selectedAnswer);
        //quizService.storeUsersAnswer(username, questionId, selectedAnswer);

        return "redirect:/questions/show?q=" + (questionIndex + 1);
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
