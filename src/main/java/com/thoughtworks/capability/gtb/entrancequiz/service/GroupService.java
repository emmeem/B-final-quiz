package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.exception.*;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private static final Integer GROUP_TRAINER_NUMBER = 2;

    private static final String GROUP_NAME_SUFFIX = "组";

    private final GroupRepository groupRepository;

    private final TraineeRepository traineeRepository;

    private final TrainerRepository trainerRepository;

    public GroupService(GroupRepository groupRepository, TraineeRepository traineeRepository,
                        TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<GtbGroup> getGroups() {
        return groupRepository.findAll();
    }

    private void init() {
        traineeRepository.clearGroupForeignKey();
        trainerRepository.clearGroupForeignKey();
        groupRepository.deleteAll();
    }

    @Transactional
    public List<GtbGroup> autoGrouping() {
        init();
        List<Trainer> allTrainer = trainerRepository.findAll();
        int trainerNumber = allTrainer.size();
        if (trainerNumber < GROUP_TRAINER_NUMBER) {
            throw new GroupingException(ExceptionMessage.GROUPING_TRAINER_COUNT_LESS_THAN_2);
        }
        grouping();

        return groupRepository.findAll();
    }

    public void grouping() {
        List<Trainer> allTrainer = trainerRepository.findAll();
        int trainerNumber = allTrainer.size();

        List<Trainee> allTrainee = traineeRepository.findAll();
        Collections.shuffle(allTrainee);
        Collections.shuffle(allTrainer);
        int traineeNumber = allTrainee.size();
        int groupNumber = trainerNumber / GROUP_TRAINER_NUMBER;
        int overflowTrainee = traineeNumber % groupNumber;

        int previousTraineeNumber = 0;
        int previousTrainerNumber = 0;

        for (int groupId = 1; groupId <= groupNumber; groupId++) {
            GtbGroup group = GtbGroup.builder().name(groupId + GROUP_NAME_SUFFIX).build();
            int groupTraineeNumber = traineeNumber / groupNumber;
            if (overflowTrainee > 0) {
                groupTraineeNumber += 1;
                overflowTrainee -= 1;
            }
            List<Trainee> trainees = allTrainee.stream()
                    .skip(previousTraineeNumber)
                    .limit(groupTraineeNumber)
                    .peek(t -> t.setGroup(group)).collect(Collectors.toList());
            List<Trainer> trainers = allTrainer.stream()
                    .skip(previousTrainerNumber)
                    .limit(GROUP_TRAINER_NUMBER)
                    .peek(t -> t.setGroup(group)).collect(Collectors.toList());
            group.setTrainees(trainees);
            group.setTrainers(trainers);

            groupRepository.save(group);
            previousTraineeNumber += groupTraineeNumber;
            previousTrainerNumber += GROUP_TRAINER_NUMBER;
        }
    }

    public void rename(String name, long id) {
        GtbGroup gtbGroup = groupRepository.findById(id)
                .orElseThrow(() -> new GroupIsNotExistException(ExceptionMessage.GROUP_NOT_EXIST));
        groupRepository.findByName(name).ifPresent(s -> {
            throw new GroupIsRepeatException(ExceptionMessage.GROUP_NAME_HAS_EXIST);});
        gtbGroup.setName(name);
        groupRepository.save(gtbGroup);
    }
}
