package com.thoughtworks.capability.gtb.entrancequiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class TraineeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotEmpty(message = ExceptionMessage.TRAINEE_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = ExceptionMessage.TRAINEE_OFFICE_NOT_EMPTY)
    private String office;

    @Email(message = ExceptionMessage.TRAINEE_EMAIL_NOT_VALID)
    @NotEmpty(message = ExceptionMessage.TRAINEE_EMAIL_NOT_EMPTY)
    private String email;

    @NotEmpty(message = ExceptionMessage.TRAINEE_GITHUB_NOT_VALID)
    private String github;

    @NotEmpty(message = ExceptionMessage.TRAINEE_ZOOM_ID_NOT_VALID)
    private String zoomId;
}
