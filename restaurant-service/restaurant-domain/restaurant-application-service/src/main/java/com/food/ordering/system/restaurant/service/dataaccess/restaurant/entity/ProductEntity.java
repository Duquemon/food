package com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
    private boolean available;
}
