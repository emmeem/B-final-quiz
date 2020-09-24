package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TrainerIsNotExistException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {

        this.trainerRepository = trainerRepository;
    }

    public Trainer getTrainer(long id) {
        return trainerRepository.findById(id).get();
    }


    public List<Trainer> getAllTrainer() {
        return trainerRepository.findAll();
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainer(long id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if(!trainer.isPresent()) {
            throw new TrainerIsNotExistException("讲师不存在");
        }
        trainerRepository.deleteById(id);
    }
}
