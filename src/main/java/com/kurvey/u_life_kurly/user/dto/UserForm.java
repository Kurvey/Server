package com.kurvey.u_life_kurly.user.dto;

import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private Long id;
    private String userId;
    private String name;
    private String password;
    private LocalDate birthDay;
    private Gender gender;
    private String role;


    public User toEntity(){
        return User.builder()
                .id(id)
                .userId(userId)
                .name(name)
                .password(password)
                .bitrhday(birthDay)
                .gender(gender)
                .role(role)
                .build();
    }
}
