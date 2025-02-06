package com.hjj.search.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hjj.search.mapper.SkuSearchMapper;
import com.hjj.search.model.SkuEs;
import com.hjj.search.service.SkuSearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //    keyword -> name
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);

        // 分组搜索
        group(queryBuilder, searchMap);

//        Page<SkuEs> page = skuSearchMapper.search(queryBuilder.build());
        AggregatedPage<SkuEs> page = (AggregatedPage<SkuEs>) skuSearchMapper.search(queryBuilder.build());


        HashMap<String, Object> resultMap = new HashMap<>();

        // 解析分组结果
        parseGroupResult(page.getAggregations(), resultMap);

        List<SkuEs> list = page.getContent();

        resultMap.put("list", list);
        resultMap.put("totalElements", page.getTotalElements());

        return resultMap;
    }

    // 分组查询
    public NativeSearchQueryBuilder group(NativeSearchQueryBuilder queryBuilder, Map<String, Object> searchMap) {
        // 如果用户没有输入分类条件，最需要将分类搜索出来，作为条件提供给用户
        if (StringUtils.isEmpty(searchMap.get("category").toString())) {
            queryBuilder.addAggregation(AggregationBuilders
                    .terms("categoryList") // 类似 map 的key 存储下面的数据
                    .field("categoryName") // 根据categoryName 进行分组
                    .size(100));

        }
        if (StringUtils.isEmpty(searchMap.get("brand").toString())) {
            queryBuilder.addAggregation(AggregationBuilders
                    .terms("brandList") // 类似 map 的key 存储下面的数据
                    .field("brandName") // 根据brand 进行分组
                    .size(100));

        }
        return queryBuilder;
        // 如果用户没有输入品牌条件，最需要将品牌搜索出来，作为条件提供给用户
    }

    // 搜索条件构建
    public NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if (searchMap != null && searchMap.size() > 0) {
            Object keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty(keywords.toString())) {
                queryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords.toString()));
            }
        }
        return queryBuilder;
    }

    // 分组结果解析
    public void parseGroupResult(Aggregations aggregations, Map<String, Object> resultMap) {
        if (aggregations == null) return;
        aggregations.forEach((agg) -> {
            // 强装
            ParsedStringTerms terms = (ParsedStringTerms) agg;
            List<String> values = new ArrayList<>();
            for (Terms.Bucket bucket : terms.getBuckets()) {
                values.add(bucket.getKeyAsString());
            }
            resultMap.put(terms.getName(), values);
        });
    }
}
