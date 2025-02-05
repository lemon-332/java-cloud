package com.hjj.good.feign;

import com.hjj.good.model.Sku;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("Good-service")
@CacheConfig(cacheNames = "ad-items-skus")
public interface SkuFeign {
    @Cacheable(key = "#id")
    List<Sku> typeSkuItems(Integer id);

    @CacheEvict(key = "#id")
    void delTypeItems(Integer id);

    @CachePut(key = "#id")
    List<Sku> updateTypeSkuItems(Integer id);
}
