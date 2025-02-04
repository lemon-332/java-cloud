package com.hjj.good.controller;

import com.hjj.good.model.Sku;
import com.hjj.good.service.SkuService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sku")
@RestController
public class SkuController {
    private final SkuService skuService;

    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @GetMapping("/adItems/type")
    public RespResult<List<Sku>> typeItems(@RequestParam Integer id) {
        return RespResult.ok(skuService.typeSkuItems(id));
    }
}
