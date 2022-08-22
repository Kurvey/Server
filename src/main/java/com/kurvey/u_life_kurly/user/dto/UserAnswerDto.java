package com.kurvey.u_life_kurly.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserAnswerDto {
    @NotNull
    private Integer numberOfFamily;

    @NotNull
    private Boolean hasBaby;

    @NotNull
    private Boolean hasDog;

    @NotNull
    private Boolean hasCat;

    @NotNull
    private Boolean hasPlant;

    @NotNull
    private List<Long> selects;
}
