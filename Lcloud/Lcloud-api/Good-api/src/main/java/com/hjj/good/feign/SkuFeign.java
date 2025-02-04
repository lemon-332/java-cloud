package com.hjj.good.feign;

import com.hjj.good.model.Sku;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("God-service")
public interface SkuFeign {
    @Cacheable(cacheNames = "ad-items-skus", key = "#id")
    List<Sku> typeSkuItems(Integer id);

    @CacheEvict(cacheNames = "ad-items-skus", key = "#id")
    void delTypeItems(Integer id);

    @CachePut(cacheNames = "ad-items-skus", key = "#id")
    List<Sku> updateTypeSkuItems(Integer id);
}
