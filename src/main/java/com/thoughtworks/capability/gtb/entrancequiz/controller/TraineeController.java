package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.capability.gtb.entrancequiz.service.TraineeService;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {

        this.traineeService = traineeService;
    }

    @PostMapping("/trainees")
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee addTrainee(@RequestBody @Valid Trainee trainee) {
        return traineeService.addTrainee(trainee);
    }

}
