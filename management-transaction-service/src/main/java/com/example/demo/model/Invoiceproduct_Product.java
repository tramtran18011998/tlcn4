package com.example.demo.model;

import com.example.demo.model.PrimaryKey.Invoiceproduct_Product_Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invoiceproduct_product")
@JsonIgnoreProperties("invoiceproduct_products")


public class Invoiceproduct_Product implements Serializable {
    @EmbeddedId
    public Invoiceproduct_Product_Id id = new Invoiceproduct_Product_Id();

    private long quantity;

    private double price;

    private double totalprice;

    @ManyToOne
    @MapsId("invoiceproduct_id")
    @JoinColumn(name = "invoiceproduct_id")
    private InvoiceProduct invoiceProduct;


//    @ManyToOne
//    @MapsId("product_id")
//    @JoinColumn(name = "product_id")
//    private Product product;

}
