package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TraineeIsNotExistException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final GroupRepository groupRepository;

    public TraineeService(TraineeRepository traineeRepository,GroupRepository groupRepository) {
        this.traineeRepository = traineeRepository;
        this.groupRepository = groupRepository;
    }

    public List<Trainee> getTrainees(boolean grouped) {
        List<GtbGroup> groups = groupRepository.findAll();
        if(grouped) {
            return traineeRepository.findAllByGroupIn(groups);
        }
        return traineeRepository.findAllByGroupEquals(null);
    }

    public Trainee addTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public void deleteTrainee(long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        if(!trainee.isPresent()) {
            throw new TraineeIsNotExistException(ExceptionMessage.TRAINEE_NOT_EXIST);
        }
        traineeRepository.deleteById(id);
    }


}
