package com.food.ordering.system.restaurant.service.domain.ports.input.service;

import com.food.ordering.system.restaurant.service.domain.dto.ProductResponseCommand;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantApplicationService {
    List<ProductResponseCommand> getListProducts();

    List<RestaurantResponse> getListRestaurant();


}
