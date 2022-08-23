package com.kurvey.u_life_kurly.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignInDto {
    @ApiModelProperty(value = "사용자 Id", example = "kurly")
    @NotNull
    private String userId;

    @ApiModelProperty(value = "비밀번호", example = "1234")
    @NotNull
    private String password;
}
