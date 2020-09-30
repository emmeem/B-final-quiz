package com.thoughtworks.capability.gtb.entrancequiz.dto;

import com.thoughtworks.capability.gtb.entrancequiz.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class ChangeGroupNameDto {
    @NotEmpty(message=ExceptionMessage.GROUP_NAME_NOT_EMPTY)
    private String Name;
}
