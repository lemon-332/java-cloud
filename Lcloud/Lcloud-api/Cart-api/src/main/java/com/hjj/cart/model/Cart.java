package com.hjj.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    private String _id;
    private String userName;
    private String name;
    private Integer price;
    private String Image;
    private String skuId;
}
