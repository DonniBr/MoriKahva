package com.example.MoriKahva;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository  extends JpaRepository <FoodOrder, Long> {
}
