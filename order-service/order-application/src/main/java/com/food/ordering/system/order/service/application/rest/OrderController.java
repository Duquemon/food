package com.food.ordering.system.order.service.application.rest;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand command) {
        log.info("Request order for customer {} at restaurant: {}", command.getCustomerId(), command.getRestaurantId());
        CreateOrderResponse order = orderApplicationService.createOrder(command);
        log.info("Order created with tracking id: {}", order.getOrderTackingId());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackId) {
        log.info("Get order by trackId: {}", trackId);
        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(new TrackOrderQuery(trackId));
        log.info("Order status is {} with tracking id {}", trackOrderResponse.getOrderStatus(), trackOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(trackOrderResponse);
    }
}
