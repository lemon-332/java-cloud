package com.hjj.good.controller;

import com.hjj.good.model.SkuAttribute;
import com.hjj.good.service.SkuAttributeService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skuAttribute")
public class SkuAttributeController {
    private final SkuAttributeService skuAttributeService;

    public SkuAttributeController(SkuAttributeService skuAttributeService) {
        this.skuAttributeService = skuAttributeService;
    }

    @GetMapping("/category/{categoryId}")
    public RespResult<List<SkuAttribute>> categorySkuAttributeList(@PathVariable(value = "categoryId") Integer categoryId) {
        List<SkuAttribute> skuAttributes = skuAttributeService.queryList(categoryId);
        return RespResult.ok(skuAttributes);
    }
}
