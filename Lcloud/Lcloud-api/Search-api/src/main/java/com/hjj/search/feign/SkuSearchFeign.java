package com.hjj.search.feign;

import com.hjj.search.model.SkuEs;
import com.hjj.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("Search-service")
public interface SkuSearchFeign {
    @PostMapping("/search/add")
    RespResult add(@RequestBody SkuEs skuEs);

    @DeleteMapping("/search/del/{id}")
    RespResult delete(@PathVariable String id);
}
