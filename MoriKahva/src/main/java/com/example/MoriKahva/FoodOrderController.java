package com.example.MoriKahva;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/foodorders")
class FoodOrderController{

    private final FoodOrderRepository repository;


    FoodOrderController(FoodOrderRepository repository){
        this.repository = repository;
    }

    //Aggregate root
    // start get-aggregate-root[]

    @GetMapping
    public List<FoodOrder> getAllFoodOrder(){
        return repository.findAll();
    }

    // end get-aggregate-root []

    @PostMapping
    public FoodOrder createFoodOrder(@RequestBody FoodOrder foodOrder){
        return repository.save(foodOrder);
    }

    //Single item

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getFoodOrderById(@PathVariable Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodOrder> updateFoodOrder(@RequestBody FoodOrder updatedFoodOrder, @PathVariable Long id){
        return repository.findById(id)
                .map(foodOrder -> {
                    foodOrder.setMeal(updatedFoodOrder.getMeal());
                    foodOrder.setBeverage(updatedFoodOrder.getBeverage());
                    return ResponseEntity.ok(repository.save(foodOrder));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodOrder(@PathVariable Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}

