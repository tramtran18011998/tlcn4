package com.example.demo.serviceimpl;

import com.example.demo.model.CustomerType;
import com.example.demo.repository.CustomerTypeRepository;
import com.example.demo.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    CustomerTypeRepository customerTypeRepository;

    @Autowired
    public CustomerTypeServiceImpl(CustomerTypeRepository customerTypeRepository){
        this.customerTypeRepository=customerTypeRepository;
    }

    @Override
    public List<CustomerType> findAllCustomerType() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Optional<CustomerType> findCustomerTypeById(long id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public void save(CustomerType customerType) {
        customerTypeRepository.save(customerType);
    }

    @Override
    public void delete(CustomerType customerType) {
        customerTypeRepository.delete(customerType);
    }
}
