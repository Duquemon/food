package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final UUID restaurantId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private final List<OrderItem> items;
    @NotNull
    private final OrderAddress address;

    public void divide(int divisor) {
        this.price = this.price.divide(new BigDecimal(divisor)).setScale(2, RoundingMode.HALF_EVEN);

        for (OrderItem item : items) {
            item.setPrice(item.getPrice()
                    .divide(new BigDecimal(divisor)).setScale(2, RoundingMode.HALF_EVEN));
            item.setSubTotal(item.getPrice()
                    .multiply(new BigDecimal(item.getQuantity())).setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
