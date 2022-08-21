package com.kurvey.u_life_kurly.user.controller;

import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import com.kurvey.u_life_kurly.user.service.LifeStyleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Api(tags = "설문조사")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lifestyle")
public class LifeStyleController {
    private final LifeStyleService lifeStyleService;

    @GetMapping
    public ResponseEntity<List<LifeStyleQuestion>> getLifeStyleQuestions(){
        return new ResponseEntity<>(lifeStyleService.getLifeStyleQuestions(), OK);
    }
}
