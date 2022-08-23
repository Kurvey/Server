package com.kurvey.u_life_kurly.error;

import com.kurvey.u_life_kurly.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response<?>> handleCustomException(final CustomException e){
        log.error(String.join(": ", e.getStatusCode().getCode(), e.getStatusCode().getMessage()));
        return new Response<>(e.getStatusCode()).toResponseEntity();
    }
}
