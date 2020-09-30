package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.dto.TraineeDto;
import com.thoughtworks.capability.gtb.entrancequiz.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.capability.gtb.entrancequiz.service.TraineeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {

        this.traineeService = traineeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TraineeDto> getTrainees(@RequestParam boolean grouped) {
        List<Trainee> trainees = traineeService.getTrainees(grouped);
        return ConvertTool.convertList(trainees, TraineeDto.class);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto addTrainee(@RequestBody @Valid TraineeDto traineeDto) {
        Trainee trainee = traineeService.addTrainee(ConvertTool.convertObject(traineeDto,Trainee.class));
        return ConvertTool.convertObject(trainee,TraineeDto.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable long id) {
        traineeService.deleteTrainee(id);
    }


}
