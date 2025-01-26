package com.example.MoriKahva;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
class FoodOrder {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String meal;
    private String beverage;

    FoodOrder() {}

    FoodOrder(String meal, String beverage){
        this.meal = meal;
        this.beverage = beverage;
    }

    public Long getId() {
        return id;
    }

    public String getMeal() {
        return meal;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    @Override
    public boolean equals(Object o){

        if (this == o)
            return (true);

        if (!(o instanceof FoodOrder))
            return false;

        FoodOrder foodOrder = (FoodOrder) o;
        return Objects.equals(this.id, foodOrder.id) && Objects.equals(this.meal, foodOrder.meal) && Objects.equals(this.beverage, foodOrder.beverage);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.meal, this.beverage);
    }

    @Override
    public String toString(){
        return "Food order{" + "id=" + this.id + ", meal='" + this.meal + '\'' + ", beverage='" + this.beverage + '\'' + '}';
    }

}

