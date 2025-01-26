package com.example.MoriKahva;

public class FoodOrderNotFoundException extends RuntimeException{
    FoodOrderNotFoundException(Long id){
        super("Could not find order" + id);
    }
}
