package project.exam_system.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.exam_system.model.view.ResultsAllViewModel;
import project.exam_system.repository.ResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/results")
public class ResultRestController {
    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;

    public ResultRestController(ResultRepository resultRepository, ModelMapper modelMapper) {
        this.resultRepository = resultRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ResultsAllViewModel>> findAll(){
        List<ResultsAllViewModel> resultsAllViewModels = resultRepository.findAll()
                .stream().map(result -> modelMapper.map(result, ResultsAllViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(resultsAllViewModels);
    }
}
