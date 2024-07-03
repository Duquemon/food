package com.food.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @NotNull
    private final UUID productId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal subTotal;

    public void setPrice(@NotNull BigDecimal price) {
        this.price = price;
    }

    public void setSubTotal(@NotNull BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
