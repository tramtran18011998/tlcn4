package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"products"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(name = "size")
    private String size;

    @Column(name = "material")
    private String material;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
    private Set<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products")
    private Category category;



//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Collection<Invoiceproduct_Product> invoiceproduct_products;


    @Column(name = "supplier_id")
    private long supplier_id;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "supplier_id")
//    private Supplier supplier;


//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = "product" , allowSetters = true)
//    private Collection<Cart> carts;


}
