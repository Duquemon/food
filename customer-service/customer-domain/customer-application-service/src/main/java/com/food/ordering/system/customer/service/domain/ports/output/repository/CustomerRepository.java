package com.food.ordering.system.customer.service.domain.ports.output.repository;

import com.food.ordering.system.customer.service.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);

    List<Customer> getAll();
}
