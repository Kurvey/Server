package com.kurvey.u_life_kurly.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class Response<T> {
    @JsonIgnore
    private HttpStatus httpStatus;
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response(StatusCode statusCode) {
        this.httpStatus = statusCode.getHttpStatus();
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public Response(StatusCode statusCode, T data) {
        this.httpStatus = statusCode.getHttpStatus();
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public ResponseEntity<Response<?>> toResponseEntity(){
        return new ResponseEntity<>(this, httpStatus);
    }

    public ResponseEntity<Response<?>> toResponseEntity(HttpHeaders headers){
        return new ResponseEntity<>(this, headers, httpStatus);
    }
}
