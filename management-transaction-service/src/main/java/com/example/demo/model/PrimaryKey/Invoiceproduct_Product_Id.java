package com.example.demo.model.PrimaryKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class Invoiceproduct_Product_Id implements Serializable {

    @Column(name = "invoiceproduct_id")
    public long invoiceproduct_id;

    @Column(name = "product_id")
    public long product_id;

}
