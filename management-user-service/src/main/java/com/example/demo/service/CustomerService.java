package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomer();
    Optional<Customer> findCustomerId(long id);
    void save(Customer customer);
    void delete(Customer customer);
}
