package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.exam_system.model.service.ResultServiceModel;
import project.exam_system.model.view.ResultsAllViewModel;
import project.exam_system.model.view.UsersAllViewModel;
import project.exam_system.service.ResultService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;
    private final ModelMapper modelMapper;

    public ResultController(ResultService resultService, ModelMapper modelMapper) {
        this.resultService = resultService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/byUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView byUsersView(ModelAndView modelAndView) {
        modelAndView.addObject("results", resultService.getAll()
                .stream()
                .map(resultServiceModel -> modelMapper.map(resultServiceModel, ResultsAllViewModel.class))
                .collect(Collectors.toList()));

        modelAndView.setViewName("results-by-name");
        return modelAndView;
    }
}
