package com.hjj.good.feign;

import com.hjj.good.model.Sku;
import com.hjj.util.RespResult;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("Good-service")
@CacheConfig(cacheNames = "ad-items-skus")
public interface SkuFeign {
    @Cacheable(key = "#id")
    @GetMapping("/sku/adItems/type")
    List<Sku> typeSkuItems(@RequestParam(value = "id") Integer id);

    @CacheEvict(key = "#id")
    @GetMapping("/sku/adItems/type")
    void delTypeItems(@RequestParam(value = "id") Integer id);

    @CachePut(key = "#id")
    @GetMapping("/sku/adItems/type")
    List<Sku> updateTypeSkuItems(@RequestParam(value = "id") Integer id);

    @GetMapping("/sku/{id}")
    RespResult<Sku> one(@PathVariable(value = "id") String id);
}
