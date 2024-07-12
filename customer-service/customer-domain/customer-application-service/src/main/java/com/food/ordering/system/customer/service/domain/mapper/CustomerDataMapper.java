package com.food.ordering.system.customer.service.domain.mapper;

import com.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;
import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName(),
                createCustomerCommand.getScoring());
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }

    public CreateCustomerCommand customerToCreateCustomerCommand(Customer customer) {
        return CreateCustomerCommand.builder()
                .customerId(customer.getId().getValue())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .scoring(customer.getScoring())
                .username(customer.getUsername())
                .build();
    }
}
