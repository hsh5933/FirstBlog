package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //Exception 문제가생기면 이쪽으로와서 메세지를 띄워주는곳
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handelArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }
}
