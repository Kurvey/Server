package com.kurvey.u_life_kurly.user.controller;

import com.kurvey.u_life_kurly.config.jwt.PrincipalDetails;
import com.kurvey.u_life_kurly.user.dto.UserAnswerDto;
import com.kurvey.u_life_kurly.user.entity.LifeStyleQuestion;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.service.LifeStyleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Api(tags = "설문조사")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lifestyle")
public class LifeStyleController {
    private final LifeStyleService lifeStyleService;

    @ApiOperation(value = "설문조사 문항 조회 API")
    @GetMapping
    public ResponseEntity<List<LifeStyleQuestion>> getLifeStyleQuestions(){
        return new ResponseEntity<>(lifeStyleService.getLifeStyleQuestions(), OK);
    }

    @ApiOperation(value = "설문조사 결과 저장 API")
    @PostMapping("{userId}")
    public ResponseEntity<?> saveLifeStyleAnswers(@PathVariable Long userId,
                                               @RequestBody @Validated UserAnswerDto userAnswerDto){
        lifeStyleService.saveLifeStyleAnswers(userId, userAnswerDto);
        return new ResponseEntity<>(OK);
    }
}
