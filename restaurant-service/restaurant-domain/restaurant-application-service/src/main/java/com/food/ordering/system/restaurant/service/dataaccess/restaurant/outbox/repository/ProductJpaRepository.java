package com.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.repository;

import com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
}
