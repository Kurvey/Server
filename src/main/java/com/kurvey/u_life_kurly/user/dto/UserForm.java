package com.kurvey.u_life_kurly.user.dto;

import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserForm {
    @ApiModelProperty(name = "사용자 Id", example = "kurly")
    @NotNull
    private String userId;

    @ApiModelProperty(name = "사용자 이름", example = "김컬리")
    @NotNull
    private String name;

    @ApiModelProperty(name = "비밀번호", example = "1234")
    @NotNull
    private String password;

    @ApiModelProperty(name = "생년월일", example = "2020-02-10")
    @NotNull
    private LocalDate birthDay;

    @ApiModelProperty(name = "성별", example = "F", notes = "M(남자), F(여자), N(성별 무관)")
    @NotNull
    private Gender gender;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .name(name)
                .password(password)
                .birthDay(birthDay)
                .gender(gender)
                .build();
    }
}
