package com.kurvey.u_life_kurly.error;

import com.kurvey.u_life_kurly.response.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final StatusCode statusCode;
}
