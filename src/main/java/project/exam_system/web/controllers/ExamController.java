package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.exam_system.model.binding.QuestionBindingModel;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.service.AnswerServiceModel;
import project.exam_system.model.service.QuestionServiceModel;
import project.exam_system.model.service.QuizServiceModel;
import project.exam_system.service.AnswerService;
import project.exam_system.service.QuestionService;
import project.exam_system.service.QuizService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exams")
public class ExamController {

    private final QuizService quizService;
    private final ModelMapper modelMapper;

    private final QuestionService questionService;
    private final AnswerService answerService;

    public ExamController(QuizService quizService, ModelMapper modelMapper, QuestionService questionService, AnswerService answerService) {
        this.quizService = quizService;
        this.modelMapper = modelMapper;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @ModelAttribute("questionBindingModel")
    public QuestionBindingModel questionBindingModel() {
        return new QuestionBindingModel();
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllExamsView(ModelAndView modelAndView) {
        modelAndView.addObject("exams", quizService.getAll());


        modelAndView.setViewName("all-exams");
        return modelAndView;
    }

    @GetMapping("/edit/{exam_id}")
    public ModelAndView editExam(@PathVariable Long exam_id, ModelAndView modelAndView) {
        modelAndView.addObject("questions", quizService.getById(exam_id).getQuestions());
        modelAndView.addObject("exam_id", exam_id);
        modelAndView.setViewName("all-questions");
        return modelAndView;
    }


    @GetMapping("/edit/{exam_id}/new_q")
    public String createNewQuestion(@ModelAttribute QuestionBindingModel questionBindingModel,
                                    Model model, @PathVariable Long exam_id) {

        model.addAttribute("numberOfAnswers", 3);
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
            return "redirect:new_q";
        }
        //TODO: binding stuff

        QuizServiceModel quizServiceModel = quizService.getById(exam_id);
/*
        List<AnswerServiceModel> answerServiceModels = new ArrayList<>();
        for (int i = 0; i < questionBindingModel.getAnswers().size(); i++){
            answerServiceModels.add(new AnswerServiceModel(questionBindingModel.getAnswers().get(i), i));
        }
*/
        QuestionServiceModel questionServiceModel = modelMapper.map(questionBindingModel, QuestionServiceModel.class).
                setQuiz(quizServiceModel).
                setOrder(quizServiceModel.getQuestions().size()).
                setAnswers(new ArrayList<>());

        questionServiceModel = questionService.save(questionServiceModel);

        List<AnswerServiceModel> answerServiceModels = new ArrayList<>();
        for (int i = 0; i < questionBindingModel.getAnswersText().size(); i++){
            answerServiceModels.add(new AnswerServiceModel(questionBindingModel.getAnswersText().get(i), i));
        }
        answerService.saveAnswers(answerServiceModels, questionServiceModel);






        System.out.println();
        return "redirect:";

    }


}
