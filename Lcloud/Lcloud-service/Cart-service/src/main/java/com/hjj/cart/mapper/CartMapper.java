package com.hjj.cart.mapper;

import com.hjj.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartMapper extends MongoRepository<Cart, String> {
}
