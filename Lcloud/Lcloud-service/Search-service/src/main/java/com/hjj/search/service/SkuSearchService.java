package com.hjj.search.service;

import com.hjj.search.model.SkuEs;

public interface SkuSearchService {
    void add(SkuEs skuEs);

    void delete(String skuId);
}
