package com.hjj.search.mapper;

import com.hjj.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {
}
