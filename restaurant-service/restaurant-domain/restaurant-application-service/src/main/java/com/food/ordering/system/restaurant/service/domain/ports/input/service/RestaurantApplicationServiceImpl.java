package com.food.ordering.system.restaurant.service.domain.ports.input.service;

import com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.ProductEntity;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.Restaurant;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.repository.ProductJpaRepository;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.repository.RestaurantOutboxJpaRepository;
import com.food.ordering.system.restaurant.service.domain.dto.ProductResponseCommand;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantResponse;
import com.food.ordering.system.restaurant.service.domain.mapper.RestaurantDataMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantApplicationServiceImpl implements RestaurantApplicationService {

    private final ProductJpaRepository productJpaRepository;

    private final RestaurantOutboxJpaRepository restaurantJpaRepository;

    private final RestaurantDataMapper restaurantDataMapper;

    public RestaurantApplicationServiceImpl(ProductJpaRepository productJpaRepository, RestaurantOutboxJpaRepository restaurantJpaRepository,
                                            RestaurantDataMapper restaurantDataMapper) {
        this.productJpaRepository = productJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataMapper = restaurantDataMapper;
    }

    @Override
    public List<ProductResponseCommand> getListProducts() {
        return productJpaRepository.findAll()
                .stream().filter(ProductEntity::isAvailable).collect(Collectors.toList())
                .stream().map(restaurantDataMapper::productEntityToProductResponseCommand).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantResponse> getListRestaurant() {
        return restaurantJpaRepository.findAll()
                .stream().filter(Restaurant::isActive).collect(Collectors.toList())
                .stream().map(restaurantDataMapper::restaurantEntityToRestaurantResponse)
                .collect(Collectors.toList());
    }


}
