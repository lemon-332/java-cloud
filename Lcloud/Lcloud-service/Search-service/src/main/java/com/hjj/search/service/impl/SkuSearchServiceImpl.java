package com.hjj.search.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hjj.search.mapper.SkuSearchMapper;
import com.hjj.search.model.SkuEs;
import com.hjj.search.service.SkuSearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

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

        attrParse(resultMap);

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
        // 属性分组查询
        queryBuilder.addAggregation(AggregationBuilders
                .terms("attrmaps")
                .field("skuAttribute")
                .size(100000));

        return queryBuilder;
        // 如果用户没有输入品牌条件，最需要将品牌搜索出来，作为条件提供给用户
    }

    // 搜索条件构建
    public NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 组合查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchMap != null && searchMap.size() > 0) {
            Object keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty(keywords.toString())) {
//                queryBuilder.withQuery(QueryBuilders.termQuery("name", keywords.toString()));
                boolQueryBuilder.must(QueryBuilders.termQuery("name", keywords.toString()));
            }
            // 分类查询
            Object category = searchMap.get("category");
            if (!StringUtils.isEmpty(keywords.toString())) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", keywords.toString()));
            }
            // 品牌查询
            Object brand = searchMap.get("brand");
            if (!StringUtils.isEmpty(keywords.toString())) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", keywords.toString()));
            }
            // 价格区间查询
            Object price = searchMap.get("price");
            if (!StringUtils.isEmpty(price.toString())) {
                String replace = price.toString().replace("元", "").replace("以上", "");
                String[] prices = replace.split("-");
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(prices[0]));
                if (prices.length == 2) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(prices[1]));
                }
            }

            // 动态属性查询
            for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
                // 一attr_ 属于动态属性
                if (entry.getKey().startsWith("attr_")) {
//                    boolQueryBuilder.must(QueryBuilders.termQuery("attrMap." + entry.getKey().replaceFirst("attr_",""), entry.getValue()));
                    boolQueryBuilder.must(QueryBuilders.termQuery("attrMap." + entry.getKey().substring(5) + ".keyword", entry.getValue()));
                }
            }
        }

        // 分页查询
        queryBuilder.withPageable(PageRequest.of(0, 100));
        return queryBuilder;
    }

    public int currentPage(Map<String, Object> searchMap) {
        try {
            Object page = searchMap.get("page");
            return ((int) page) - 1;
        } catch (Exception e) {
            return 0;
        }
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

    // 将属性信息合并成map
    public void attrParse(Map<String, Object> searchMap) {
        Object attrmaps = searchMap.get("attrmaps");
        if (attrmaps == null) return;
        List<String> groupList = (List<String>) attrmaps;
        Map<String, Set<Object>> allMaps = new HashMap<>();

        for (String attr : groupList) {
            Map<String, Object> map = JSON.parseObject(attr, Map.class);

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Set<Object> values = allMaps.get(key);
                if (values == null) {
                    values = new HashSet<>();
                }
                values.add(entry.getValue());
                allMaps.put(key, values);
            }
        }
        searchMap.put("attrmaps", allMaps);
    }
}
