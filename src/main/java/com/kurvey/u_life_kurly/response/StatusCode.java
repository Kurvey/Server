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
    USER_NOT_FOUND(BAD_REQUEST, "E1", "존재하지 않는 사용자입니다"),
    INCORRECT_PASSWORD(BAD_REQUEST, "E1", "비밀번호가 맞지 않습니다")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
