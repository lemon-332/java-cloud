package com.hjj.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.good.model.Sku;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface SkuService extends IService<Sku> {
    /**
     * 根据推广产品分类Id查询指定分类下的产品列表
     *
     * @param id
     * @return
     */
    List<Sku> typeSkuItems(Integer id);

    void delTypeItems(Integer id);

    @CachePut(cacheNames = "ad-items-skus", key = "#id")
    List<Sku> updateTypeSkuItems(Integer id);
}
