package com.kurvey.u_life_kurly.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    SUCCESS(OK, "C0", "요청을 성공적으로 처리했습니다"),
    CREATE(CREATED, "C1", "생성을 완료했습니다"),

    DUPLICATED_ID(CONFLICT, "E0", "중복된 Id입니다"),
    USER_NOT_FOUND(NOT_FOUND, "E1", "존재하지 않는 사용자입니다"),
    INCORRECT_PASSWORD(BAD_REQUEST, "E2", "비밀번호가 맞지 않습니다"),
    INVALID_INPUT(BAD_REQUEST, "E3", "입력이 규칙에 맞지 않습니다"),
    QUESTION_NOT_FOUND(NOT_FOUND, "E4", "존재하지 않는 문항이 입력되었습니다"),
    USER_INFO_DOES_NOT_EXIST(NOT_FOUND, "E5", "유저 정보가 입력되지 않았습니다."),

    SELECTION_SET_GENERATION_FAULT(INTERNAL_SERVER_ERROR, "ES0", "선택지 조합 생성 과정에서 문제가 발생했습니다"),
    PRIORITY_WEIGHTS_NOT_SET(INTERNAL_SERVER_ERROR, "ES1", "우선순위 가중치 값이 세팅되지 않았습니다"),
    REGENERATE_SELECTION_SET(INTERNAL_SERVER_ERROR, "ES2", "선택지 조합을 다시 생성했습니다. 다시 한번 시도해주세요")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
