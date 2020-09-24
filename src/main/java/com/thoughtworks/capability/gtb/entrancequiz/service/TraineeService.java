package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TraineeIsNotExistException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {

        this.traineeRepository = traineeRepository;
    }

    public Trainee addTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public void deleteTrainee(long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        if(!trainee.isPresent()) {
            throw new TraineeIsNotExistException("学员不存在");
        }
        traineeRepository.deleteById(id);
    }


}
