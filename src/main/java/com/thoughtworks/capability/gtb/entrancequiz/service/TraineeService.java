package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;


@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {

        this.traineeRepository = traineeRepository;
    }

    public Trainee addTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }


}
