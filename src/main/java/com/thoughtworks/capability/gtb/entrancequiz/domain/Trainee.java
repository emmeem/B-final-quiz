package com.thoughtworks.capability.gtb.entrancequiz.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String office;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String github;

    @NotEmpty
    private String zoomId;
}
