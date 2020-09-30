package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.dto.TrainerDto;
import com.thoughtworks.capability.gtb.entrancequiz.service.TrainerService;
import com.thoughtworks.capability.gtb.entrancequiz.utils.ConvertTool;
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
    public List<TrainerDto> getTrainers(@RequestParam boolean grouped) {
        List<Trainer> trainers = trainerService.getTrainers(grouped);
        return ConvertTool.convertList(trainers, TrainerDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDto addTrainer(@RequestBody @Valid TrainerDto trainerDto) {
       Trainer trainer = trainerService.addTrainer(ConvertTool.convertObject(trainerDto, Trainer.class));
       return ConvertTool.convertObject(trainer, TrainerDto.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable long id) {
        trainerService.deleteTrainer(id);
    }

}
