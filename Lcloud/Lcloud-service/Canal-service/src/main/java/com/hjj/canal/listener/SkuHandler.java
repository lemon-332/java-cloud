package com.hjj.canal.listener;

import com.alibaba.fastjson.JSON;
import com.hjj.good.model.Sku;
import com.hjj.search.feign.SkuSearchFeign;
import com.hjj.search.model.SkuEs;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable("sku")
public class SkuHandler implements EntryHandler<Sku> {

    private final SkuSearchFeign skuSearchFeign;

    public SkuHandler(SkuSearchFeign skuSearchFeign) {
        this.skuSearchFeign = skuSearchFeign;
    }

    @Override
    public void insert(Sku sku) {
        if (sku.getStatus() == 1) {
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }
    }

    @Override
    public void update(Sku before, Sku after) {
        if (after.getStatus() == 2) {
            skuSearchFeign.delete(after.getId().toString());
        } else {
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
    }

    @Override
    public void delete(Sku sku) {
        skuSearchFeign.delete(sku.getId().toString());
    }
}
