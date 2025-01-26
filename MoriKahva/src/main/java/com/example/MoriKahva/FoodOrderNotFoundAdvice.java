package com.example.MoriKahva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class FoodOrderNotFoundAdvice{

    @ExceptionHandler(FoodOrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String foodOrderNotFoundHandler(FoodOrderNotFoundException ex){
        return ex.getMessage();
    }
}
