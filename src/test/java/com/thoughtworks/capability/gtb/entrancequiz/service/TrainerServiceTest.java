package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {
    private TrainerService trainerService;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private GroupRepository groupRepository;

    private Trainer trainer;
    private long trainer_id;

    @BeforeEach
    void setUp() {
        trainerService = new TrainerService(trainerRepository,groupRepository);
        trainer = trainer.builder()
                .id(123L)
                .name("Panda")
                .build();
        trainer_id = trainer.getId();
    }

    @Nested
    class addTrainer {
        @Test
        public void should_return_Trainer() {
            when(trainerRepository.save(trainer)).thenReturn(trainer);

            verify(trainerRepository).save(trainer);
        }
    }
}