package com.food.ordering.system.restaurant.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantResponse {
    private String id;
    private String name;
}
