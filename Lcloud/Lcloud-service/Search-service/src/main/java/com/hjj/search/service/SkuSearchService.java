package com.hjj.search.service;

import com.hjj.search.model.SkuEs;

import java.util.Map;

public interface SkuSearchService {

    Map<String, Object> search(Map<String, Object> searchMap);

    void add(SkuEs skuEs);

    void delete(String skuId);
}
