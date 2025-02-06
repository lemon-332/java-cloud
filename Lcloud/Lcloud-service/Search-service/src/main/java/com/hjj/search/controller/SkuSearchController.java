package com.hjj.search.controller;

import com.hjj.search.model.SkuEs;
import com.hjj.search.service.SkuSearchService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SkuSearchController {
    private final SkuSearchService skuSearchService;

    public SkuSearchController(SkuSearchService skuSearchService) {
        this.skuSearchService = skuSearchService;
    }

    @PostMapping("/add")
    public RespResult add(@RequestBody SkuEs skuEs) {
        skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    @DeleteMapping("/del/{id}")
    public RespResult delete(@PathVariable String id) {
        skuSearchService.delete(id);
        return RespResult.ok();
    }
}
