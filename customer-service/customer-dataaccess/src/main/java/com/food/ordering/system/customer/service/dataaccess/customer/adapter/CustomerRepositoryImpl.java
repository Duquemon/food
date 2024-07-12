package com.food.ordering.system.customer.service.dataaccess.customer.adapter;

import com.food.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import com.food.ordering.system.customer.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.customer.service.dataaccess.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.customer.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }

    @Override
    public List<Customer> getAll() {
        List<CustomerEntity> all = customerJpaRepository.findAll();
        return all.stream().map(customerDataAccessMapper::customerEntityToCustomer)
                .collect(Collectors.toList());
    }
}
