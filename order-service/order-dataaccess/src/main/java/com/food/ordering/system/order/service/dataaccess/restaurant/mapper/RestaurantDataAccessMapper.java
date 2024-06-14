package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurant =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found"));

        List<Product> restaurantProducts = restaurantEntities.stream()
                .map(restaurantEntity -> new Product(
                        new ProductId(restaurantEntity.getProductId()), restaurantEntity.getProductName(),
                        new Money(restaurantEntity.getProductPrice())))
                .collect(Collectors.toList());
        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurant.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurant.getRestaurantActive())
                .build();
    }
}
