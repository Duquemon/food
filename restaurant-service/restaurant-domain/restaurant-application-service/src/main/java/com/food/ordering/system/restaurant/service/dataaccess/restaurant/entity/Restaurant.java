package com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurants")
@Entity
public class Restaurant {

    @Id
    private UUID id;
    private String name;
    private boolean active;
}
