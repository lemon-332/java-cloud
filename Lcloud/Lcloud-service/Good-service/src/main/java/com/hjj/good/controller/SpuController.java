package com.hjj.good.controller;

import com.hjj.good.model.Product;
import com.hjj.good.service.SpuService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    private final SpuService spuService;

    public SpuController(SpuService spuService) {
        this.spuService = spuService;
    }

    @PostMapping("/save")
    public RespResult save(@RequestBody Product product) {
        spuService.saveProduct(product);
        return RespResult.ok();
    }
}
