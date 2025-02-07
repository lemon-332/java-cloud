package com.hjj.cart.service.impl;

import com.hjj.cart.mapper.CartMapper;
import com.hjj.cart.model.Cart;
import com.hjj.cart.service.CartService;
import com.hjj.good.feign.SkuFeign;
import com.hjj.good.model.Sku;
import com.hjj.util.RespResult;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final SkuFeign skuFeign;

    public CartServiceImpl(CartMapper cartMapper, SkuFeign skuFeign) {
        this.cartMapper = cartMapper;
        this.skuFeign = skuFeign;
    }

    @Override
    public void add(String id, String userName, Integer num) {
        cartMapper.deleteById(userName + id);
        if (num > 0) {
            RespResult<Sku> skuRespResult = skuFeign.one(id);
            Sku sku = skuRespResult.getData();
            if (sku != null) {
                Cart cart = new Cart(userName + id, userName, sku.getName(), sku.getPrice(), sku.getImage(), id);
                cartMapper.save(cart);
            }
        }
    }
}
