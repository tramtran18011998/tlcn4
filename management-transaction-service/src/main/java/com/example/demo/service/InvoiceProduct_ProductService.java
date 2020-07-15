package com.example.demo.service;

import com.example.demo.model.Invoiceproduct_Product;

import java.util.List;
import java.util.Optional;

public interface InvoiceProduct_ProductService {
    List<Invoiceproduct_Product> findAllInvoiceproduct_Product();
    Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById(long id);
    //Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById2(Invoiceproduct_Product_Id id);
    void save(Invoiceproduct_Product invoiceproduct_product);
    void delete(Invoiceproduct_Product invoiceproduct_product);
}
