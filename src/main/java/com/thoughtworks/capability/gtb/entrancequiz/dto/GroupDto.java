package com.thoughtworks.capability.gtb.entrancequiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class GroupDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotEmpty(message=ExceptionMessage.GROUP_NAME_NOT_EMPTY)
    private String name;

    private List<Trainee> trainees;

    private List<Trainer> trainers;
}
