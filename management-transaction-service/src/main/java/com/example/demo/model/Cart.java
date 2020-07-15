package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
@JsonIgnoreProperties({"products"})
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "product_id")
//    @JsonIgnoreProperties(value = "carts", allowSetters = true)
//    private Product product;
    @Column(name = "product_id")
    private long product_id;

    private String productname;

    private long quantity;

    private double price;

    private double totalprice;

    private int status;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    @JsonIgnoreProperties(value = "carts", allowSetters = true)
//    private Customer customer;
    @Column(name = "customer_id")
    private long customer_id;

}
