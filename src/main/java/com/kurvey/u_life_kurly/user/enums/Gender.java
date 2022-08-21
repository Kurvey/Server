package com.kurvey.u_life_kurly.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("남자"),
    F("여자");
    private String description;
}
