package com.hjj.canal.listener;

import com.hjj.good.feign.SkuFeign;
import com.hjj.good.model.AdItems;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable("ad_items")
public class AdItemsHandler implements EntryHandler<AdItems> {

    private final SkuFeign skuFeign;

    public AdItemsHandler(SkuFeign skuFeign) {
        this.skuFeign = skuFeign;
    }


    @Override
    public void insert(AdItems adItems) {
        skuFeign.updateTypeSkuItems(adItems.getId());
    }

    @Override
    public void update(AdItems before, AdItems after) {
        if (before.getType().intValue() != after.getType().intValue()) {
            skuFeign.delTypeItems(before.getId());
        }
        skuFeign.updateTypeSkuItems(after.getId());
    }

    @Override
    public void delete(AdItems adItems) {
        skuFeign.delTypeItems(adItems.getId());
    }
}
