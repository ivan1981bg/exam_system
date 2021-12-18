package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.entities.UserAnswer;
import project.exam_system.model.service.ExamServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.model.view.QuestionViewModel;
import project.exam_system.repository.UserAnswerRepository;
import project.exam_system.service.AnswerService;
import project.exam_system.service.ExamService;
import project.exam_system.service.QuestionService;
import project.exam_system.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final ExamService examService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final AnswerService answerService;


    public QuestionController(ExamService examService, QuestionService questionService, ModelMapper modelMapper, UserService userService, AnswerService answerService) {
        this.examService = examService;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.answerService = answerService;
    }



    @GetMapping("/show")
    public String showQuestion( @RequestParam(name = "e") Long examId,
                               @RequestParam(name = "q") Integer questionIndex,
                               Model model) {

        ExamServiceModel examServiceModel = examService.getById(examId);
        int numberOfQuestions = examServiceModel.getQuestions().size();
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
            return "redirect:/exams/completed/" + examId;
        }
    }

    @PostMapping("/show")
    public String userAnswered( Principal principal,
                                @RequestParam(name = "e") Long examId,
                                @RequestParam(name = "selectedAnswer", defaultValue = "") String selectedAnswer,
                                @RequestParam(name = "q") int questionIndex,
                                RedirectAttributes redirectAttributes) {

        if (selectedAnswer.isBlank()) {
            redirectAttributes.addFlashAttribute("message", "Please select an answer before clicking the submit button.");
            return "redirect:show?e=" + examId + "&q=" + questionIndex;
        }
        userService.storeUserAnswer(principal.getName(), examId,questionIndex, selectedAnswer);

        return "redirect:/questions/show?e=" + examId + "&q=" + (questionIndex + 1);
    }

    @PostMapping("/skip")
    public String skip( Principal principal,
                                @RequestParam(name = "e") Long examId,
                                @RequestParam(defaultValue = "") String selectedAnswer,
                                @RequestParam(name = "q") int questionIndex) {


        String username = principal.getName();
        userService.storeUserAnswer(username, examId,questionIndex, selectedAnswer);

        return "redirect:/questions/show?e=" + examId + "&q=" + (questionIndex + 1);
    }

    @PostMapping("/delete")
    public String deleteQuestion(@RequestParam Long qId,
                                 @RequestParam Long examId, RedirectAttributes redirectAttrs) {

        String redirectModifier = "";
        userService.deleteUserAnswerByQuestionId(qId);
        answerService.deleteAnswersQuestion(qId);

        redirectAttrs.addAttribute("examId", examId);

        return "redirect:/exams/edit/{examId}";

    }
}
