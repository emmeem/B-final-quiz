package com.thoughtworks.capability.gtb.entrancequiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupNumDto {
    private int trainerNum;

    private int traineeNum;

    private int groupNum;

    private int overflowTrainee;


}
