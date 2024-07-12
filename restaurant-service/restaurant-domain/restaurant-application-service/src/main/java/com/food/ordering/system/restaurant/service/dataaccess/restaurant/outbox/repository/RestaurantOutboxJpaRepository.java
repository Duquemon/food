package com.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.repository;

import com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantOutboxJpaRepository  extends JpaRepository<Restaurant, UUID> {
}
