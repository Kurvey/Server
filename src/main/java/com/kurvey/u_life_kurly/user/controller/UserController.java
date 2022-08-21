package com.kurvey.u_life_kurly.user.controller;

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

import static org.springframework.http.HttpStatus.*;

@Api(tags = "회원가입 / 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/signup")
    public ResponseEntity createUser(@RequestBody @Validated UserForm form){
        userService.creatUser(form);

        return new ResponseEntity(CREATED);
    }

    @ApiOperation(value = "로그인 API")
    @PostMapping("signin")
    public ResponseEntity<?> authoirize(@RequestBody @Validated SignInDto signInDto){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", userService.authorize(signInDto));
        return new ResponseEntity<>(headers, OK);
    }
}
