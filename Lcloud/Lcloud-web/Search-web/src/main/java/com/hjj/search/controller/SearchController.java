package com.hjj.search.controller;


import com.hjj.search.feign.SkuSearchFeign;
import com.hjj.util.RespResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/web/search")
public class SearchController {

    private final SkuSearchFeign skuSearchFeign;

    public SearchController(SkuSearchFeign skuSearchFeign) {
        this.skuSearchFeign = skuSearchFeign;
    }

    @GetMapping
    public String search(@RequestParam Map<String, Object> searchMap, Model model) {
        RespResult<Map<String, Object>> result = skuSearchFeign.search(searchMap);
        model.addAttribute("result", result.getData());
        return "search";
    }
}
