package com.hjj.search.feign;

import com.hjj.search.model.SkuEs;
import com.hjj.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("Search-service")
public interface SkuSearchFeign {
    @PostMapping("/search/add")
    RespResult add(@RequestBody SkuEs skuEs);

    @DeleteMapping("/search/del/{id}")
    RespResult delete(@PathVariable String id);

    @GetMapping("/search")
    RespResult<Map<String, Object>> search(@RequestParam Map<String, Object> searchMap);
}
