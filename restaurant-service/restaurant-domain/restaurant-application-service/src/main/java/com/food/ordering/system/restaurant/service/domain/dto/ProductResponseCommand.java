package com.food.ordering.system.restaurant.service.domain.dto;

import com.food.ordering.system.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseCommand {
    private UUID id;
    private String name;
    private Money price;
    private final int quantity;
}
