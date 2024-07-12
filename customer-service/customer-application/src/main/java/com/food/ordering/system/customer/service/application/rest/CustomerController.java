package com.food.ordering.system.customer.service.application.rest;

import com.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;
import com.food.ordering.system.customer.service.domain.ports.input.service.CustomerApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RequestMapping(value = "/customers", produces = "application/vnd.api.v1+json")
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    public CustomerController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerCommand
                                                                             createCustomerCommand) {
        log.info("Creating customer with username: {}", createCustomerCommand.getUsername());
        CreateCustomerResponse response = customerApplicationService.createCustomer(createCustomerCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CreateCustomerCommand>> getListCustomer() {
        log.info("Get all customer");
        return ResponseEntity.ok(customerApplicationService.getListCustomer());

    }

}
