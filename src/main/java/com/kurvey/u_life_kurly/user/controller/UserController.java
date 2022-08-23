package com.kurvey.u_life_kurly.user.controller;

import com.kurvey.u_life_kurly.response.Response;
import com.kurvey.u_life_kurly.user.dto.SignInDto;
import com.kurvey.u_life_kurly.user.dto.UserForm;
import com.kurvey.u_life_kurly.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.kurvey.u_life_kurly.response.StatusCode.CREATE;
import static com.kurvey.u_life_kurly.response.StatusCode.SUCCESS;
import static org.springframework.http.HttpStatus.CREATED;

@Api(tags = "회원가입 / 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입 API")
    @ResponseStatus(CREATED)
    @PostMapping("signup")
    public ResponseEntity<Response<?>> createUser(@RequestBody @Validated UserForm form){
        Long id = userService.creatUser(form);
        return new Response<>(CREATE, Map.of("id", id)).toResponseEntity();
    }

    @ApiOperation(value = "로그인 API")
    @PostMapping("signin")
    public ResponseEntity<Response<?>> authoirize(@RequestBody @Validated SignInDto signInDto){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", userService.authorize(signInDto));
        return new Response<>(SUCCESS).toResponseEntity(headers);
    }
}


/*
        "id" : "1",
        "deliveryType": "샛별배송",
        "productName": "아오리 사과 1.5kg(10입내)",
        "cost": "0",
        "description": "풋풋한 매력 가득한 제철 사과"
        }
*/
