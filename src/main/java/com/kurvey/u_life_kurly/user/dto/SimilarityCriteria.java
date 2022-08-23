package com.kurvey.u_life_kurly.user.dto;

import com.kurvey.u_life_kurly.user.entity.UserInfo;
import com.kurvey.u_life_kurly.user.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SimilarityCriteria {
    private final LocalDate ageRangeFrom;
    private final LocalDate ageRangeTo;
    private final Gender gender;
    private final Integer numberOfFamily;
    private final Boolean hasBaby;
    private final Boolean hasDog;
    private final Boolean hasCat;
    private final Boolean hasPlant;

    public SimilarityCriteria(LocalDate birthday, Gender gender, UserInfo userInfo) {
        int thisYear = LocalDate.now().getYear();
        this.ageRangeFrom = LocalDate.of(thisYear - 5, 1, 1);
        this.ageRangeTo = LocalDate.of(thisYear + 5, 1, 1);
        this.gender = gender;
        this.numberOfFamily = userInfo.getNumberOfFamily();
        this.hasBaby = userInfo.getHasBaby();
        this.hasDog = userInfo.getHasDog();
        this.hasCat = userInfo.getHasCat();
        this.hasPlant = userInfo.getHasPlant();
    }
}
