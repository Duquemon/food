package com.food.ordering.system.restaurant.service.application.rest;

import com.food.ordering.system.restaurant.service.domain.dto.ProductResponseCommand;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantResponse;
import com.food.ordering.system.restaurant.service.domain.ports.input.service.RestaurantApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RequestMapping(value = "/restaurants", produces = "application/vnd.api.v1+json")
public class RestaurantController {

    private final RestaurantApplicationService restaurantApplicationService;

    public RestaurantController(RestaurantApplicationService restaurantApplicationService) {
        this.restaurantApplicationService = restaurantApplicationService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductResponseCommand>> getListCustomer() {
        return ResponseEntity.ok(restaurantApplicationService.getListProducts());
    }

    @GetMapping(value = "/restaurants")
    public ResponseEntity<List<RestaurantResponse>> getListRestaurant() {
        return ResponseEntity.ok(restaurantApplicationService.getListRestaurant());
    }
}
