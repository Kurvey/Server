package com.kurvey.u_life_kurly.user.controller;

import com.kurvey.u_life_kurly.user.dto.UserForm;
import com.kurvey.u_life_kurly.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/signup")
    public ResponseEntity createUser(@RequestBody @Validated UserForm form){
        userService.creatUser(form);

        return new ResponseEntity(CREATED);
    }
}
