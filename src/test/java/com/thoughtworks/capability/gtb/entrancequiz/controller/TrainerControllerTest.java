package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(TrainerController.class)
public class TrainerControllerTest {
    @MockBean
    private TrainerService trainerService;
    @Autowired
    private MockMvc mockMvc;

    private Trainer trainer;
    private long trainer_id;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        trainer = trainer.builder()
                .id(123L)
                .name("Panda")
                .build();
        trainer_id = trainer.getId();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(trainerService);
    }

    @Nested
    class getTrainers{
        @Test
        public void should_return_trainers() throws Exception {
            List<Trainer> trainers = new ArrayList<>();
            trainers.add(trainer);

            when(trainerService.getTrainers(false)).thenReturn(trainers);

            mockMvc.perform(get("/trainers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name", is("Panda")));
        }
    }

    @Nested
    class addTrainer{
        @Test
        public void should_return_trainer_id_when_add_trainer() throws Exception {
            when(trainerService.addTrainer(trainer)).thenReturn(trainer);

            String jsonData = objectMapper.writeValueAsString(trainer);
            mockMvc.perform(post("/trainers")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class deleteTrainer{
        @Test
        public void should_delete_trainer() throws Exception {
            mockMvc.perform(delete("/trainers/{id}",trainer_id))
                    .andExpect(status().isNoContent());
        }
    }
}
