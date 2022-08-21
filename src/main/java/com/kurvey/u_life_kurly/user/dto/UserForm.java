package com.kurvey.u_life_kurly.user.dto;

import com.kurvey.u_life_kurly.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private Long id;
    private String userId;
    private String password;

    public User toEntity(){
        return User.builder()
                .id(id)
                .userId(userId)
                .password(password)
                .build();
    }
}
