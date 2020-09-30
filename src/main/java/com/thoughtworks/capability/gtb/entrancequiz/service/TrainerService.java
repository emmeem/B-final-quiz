package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TrainerIsNotExistException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final GroupRepository groupRepository;

    public TrainerService(TrainerRepository trainerRepository, GroupRepository groupRepository) {

        this.trainerRepository = trainerRepository;
        this.groupRepository = groupRepository;
    }

    public List<Trainer> getTrainers(boolean grouped) {
        List<GtbGroup> groups = groupRepository.findAll();
        if(grouped) {
            return trainerRepository.findAllByGroupIn(groups);
        }
        return trainerRepository.findAllByGroupEquals(null);
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainer(long id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if(!trainer.isPresent()) {
            throw new TrainerIsNotExistException(ExceptionMessage.TRAINER_NOT_EXIST);
        }
        trainerRepository.deleteById(id);
    }
}
