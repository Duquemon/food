package com.food.ordering.system.customer.service.domain.ports.input.service;

import com.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;

import javax.validation.Valid;
import java.util.List;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

    List<CreateCustomerCommand> getListCustomer();

}
