package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {

        this.trainerService = trainerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trainer> getTrainers() {
        return trainerService.getAllTrainer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer addTrainer(@RequestBody @Valid Trainer trainer) {

        return trainerService.addTrainer(trainer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable long id) {
        trainerService.deleteTrainer(id);
    }

}
