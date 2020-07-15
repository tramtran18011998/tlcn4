package com.example.demo.service;

import com.example.demo.model.CustomerType;
import java.util.List;
import java.util.Optional;

public interface CustomerTypeService {
    List<CustomerType> findAllCustomerType();
    Optional<CustomerType> findCustomerTypeById(long id);
    void save(CustomerType customerType);
    void delete(CustomerType customerType);

}
