package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.service.TraineeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(TraineeController.class)
public class TraineeControllerTest {
    @MockBean
    private TraineeService traineeService;
    @Autowired
    private MockMvc mockMvc;


    private Trainee trainee;
    private long trainee_id;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        trainee = trainee.builder()
                .id(123L)
                .name("Panda")
                .office("深圳")
                .email("bar@thoughtworks.com")
                .github("bar")
                .zoomId("bar")
                .build();
        trainee_id = trainee.getId();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(traineeService);
    }

    @Nested
    class getTrainees{
        @Test
        public void should_return_trainees() throws Exception {
            List<Trainee> trainees = new ArrayList<>();
            trainees.add(trainee);

            when(traineeService.getAllTrainee()).thenReturn(trainees);

            mockMvc.perform(get("/trainees"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name", is("Panda")));
        }
    }

    @Nested
    class addTrainee{
        @Test
        public void should_return_trainee_id_when_add_trainee() throws Exception {
            when(traineeService.addTrainee(trainee)).thenReturn(trainee);

            String jsonData = objectMapper.writeValueAsString(trainee);
            mockMvc.perform(post("/trainees")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class deleteTrainee{
        @Test
        public void should_delete_trainee() throws Exception {

            mockMvc.perform(delete("/trainees/{id}",trainee_id))
                    .andExpect(status().isNoContent());
        }
    }
}

