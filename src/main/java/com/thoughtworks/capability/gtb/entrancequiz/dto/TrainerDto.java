package com.thoughtworks.capability.gtb.entrancequiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class TrainerDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotEmpty(message = ExceptionMessage.TRAINER_NAME_NOT_EMPTY)
    private String name;
}
