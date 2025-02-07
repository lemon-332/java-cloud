package com.hjj.cart.controller;

import com.hjj.cart.service.CartService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}/{num}")
    public RespResult add(@PathVariable String id,
                          @PathVariable Integer num) {
        cartService.add(id, "user", num);
        return RespResult.ok();
    }
}
