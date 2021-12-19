package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.binding.QuestionBindingModel;
import project.exam_system.model.binding.UserRegisterBindingModel;
import project.exam_system.model.entities.Exam;
import project.exam_system.model.service.*;
import project.exam_system.service.*;
import project.exam_system.web.annotations.PageTitle;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;
    private final ModelMapper modelMapper;

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    private final ResultService resultService;

    public ExamController(ExamService examService, ModelMapper modelMapper, QuestionService questionService, AnswerService answerService, UserService userService, ResultService resultService) {
        this.examService = examService;
        this.modelMapper = modelMapper;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
        this.resultService = resultService;
    }

    @ModelAttribute("questionBindingModel")
    public QuestionBindingModel createBindingModel() {
        return new QuestionBindingModel();
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All exams")
    public ModelAndView getAllExamsView(ModelAndView modelAndView) {
        modelAndView.addObject("exams", examService.getAll());


        modelAndView.setViewName("all-exams");
        return modelAndView;
    }

    @GetMapping("/edit/{exam_id}")
    public ModelAndView editExam(@PathVariable Long exam_id, ModelAndView modelAndView) {
        modelAndView.addObject("questions", examService.getById(exam_id).getQuestions());
        modelAndView.addObject("exam_id", exam_id);
        modelAndView.setViewName("all-questions");
        return modelAndView;
    }


    @GetMapping("/edit/{exam_id}/new_q")
    public String createNewQuestion(@RequestParam(value = "numberOfAnswers", defaultValue = "3") Integer numberOfAnswers,
                                    Model model,
                                    @PathVariable Long exam_id) {

        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("MAX_ANSWERS", 5);
        return "new-question";
    }



    @PostMapping("/edit/{exam_id}/new_q")
    public String newQuestion(@Valid QuestionBindingModel questionBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model, @PathVariable Long exam_id) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("questionBindingModel", questionBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.questionBindingModel", bindingResult);


            redirectAttributes.addAttribute("numberOfAnswers", questionBindingModel.getAnswersText().size());
            return "redirect:new_q";
        }

        ExamServiceModel examServiceModel = examService.getById(exam_id);

        QuestionServiceModel questionServiceModel = modelMapper.map(questionBindingModel, QuestionServiceModel.class).
                setExam(examServiceModel).
                setOrder(examServiceModel.getQuestions().size()).
                setAnswers(new ArrayList<>());


        questionServiceModel = questionService.save(questionServiceModel);


        List<AnswerServiceModel> answerServiceModels = new ArrayList<>();
        for (int i = 0; i < questionBindingModel.getAnswersText().size(); i++){
            answerServiceModels.add(new AnswerServiceModel(questionBindingModel.getAnswersText().get(i), i));
        }
        answerService.saveAnswers(answerServiceModels, questionServiceModel);
        return "redirect:";

    }

    @GetMapping("/start/{examId}")
    public String startExam(@PathVariable Long examId, Model model ) {



        return "forward:/questions/show?e=" + examId + "&q=0";
    }

    @GetMapping("/completed/{examId}")
    @PageTitle("Completition")
    public String onCompletion(@PathVariable Long examId, Principal principal, ModelMap model) {

        UserServiceModel userServiceModel = userService.getByUsername(principal.getName());
        Integer totalCorrect = answerService.getTotalCorrect(userServiceModel, examService.getById(examId));


        resultService.saveResult(userServiceModel, examService.getById(examId),totalCorrect);
        model.addAttribute("score", totalCorrect);
        model.addAttribute("totalQuestions", examService.getById(examId).getQuestions().size());
        return "exam-completion";

            }
}
