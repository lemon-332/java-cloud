package com.hjj.search.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hjj.search.mapper.SkuSearchMapper;
import com.hjj.search.model.SkuEs;
import com.hjj.search.service.SkuSearchService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    private final SkuSearchMapper skuSearchMapper;

    public SkuSearchServiceImpl(SkuSearchMapper skuSearchMapper) {
        this.skuSearchMapper = skuSearchMapper;
    }

    @Override
    public void add(SkuEs skuEs) {
        String attribute = skuEs.getSkuAttribute();
        if (!StringUtils.isEmpty(attribute)) {
            skuEs.setAttrMap(JSON.parseObject(attribute, Map.class));
        }
        skuSearchMapper.save(skuEs);
    }

    @Override
    public void delete(String id) {
        skuSearchMapper.deleteById(id);
    }
}
