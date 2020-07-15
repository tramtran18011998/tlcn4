package com.example.demo.serviceimpl;

import com.example.demo.model.Invoiceproduct_Product;
import com.example.demo.repository.InvoiceProduct_ProductRepository;
import com.example.demo.service.InvoiceProduct_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceProduct_ProductServiceImpl implements InvoiceProduct_ProductService {

    InvoiceProduct_ProductRepository invoiceProduct_productRepository;

    @Autowired
    public InvoiceProduct_ProductServiceImpl(InvoiceProduct_ProductRepository invoiceProduct_productRepository){
        this.invoiceProduct_productRepository=invoiceProduct_productRepository;
    }

    @Override
    public List<Invoiceproduct_Product> findAllInvoiceproduct_Product() {
        return invoiceProduct_productRepository.findAll();
    }

    @Override
    public Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById(long id) {
        return invoiceProduct_productRepository.findById(id);
    }



    @Override
    public void save(Invoiceproduct_Product invoiceproduct_product) {
        invoiceProduct_productRepository.save(invoiceproduct_product);
    }

    @Override
    public void delete(Invoiceproduct_Product invoiceproduct_product) {
        invoiceProduct_productRepository.delete(invoiceproduct_product);
    }
}
