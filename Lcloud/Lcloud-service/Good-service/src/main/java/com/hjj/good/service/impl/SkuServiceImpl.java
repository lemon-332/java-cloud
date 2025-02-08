package com.hjj.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.good.mapper.AdItemsMapper;
import com.hjj.good.mapper.SkuMapper;
import com.hjj.good.model.AdItems;
import com.hjj.good.model.Sku;
import com.hjj.good.service.SkuService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "ad-items-skus") // 下面就可以不用写cacheNames了
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    private final AdItemsMapper adItemsMapper;

    public SkuServiceImpl(AdItemsMapper adItemsMapper) {
        this.adItemsMapper = adItemsMapper;
    }

    @Cacheable(cacheNames = "ad-items-skus", key = "#id")
    @Override
    public List<Sku> typeSkuItems(Integer id) {
        List<AdItems> adItems = adItemsMapper.selectList(new QueryWrapper<AdItems>().eq("type", id));
//        List<Sku> skus = new ArrayList<>();
//        if (adItems != null && adItems.size() > 0) {
//            for (AdItems adItem : adItems) {
//                skus.add(baseMapper.selectById(adItem.getSkuId()));
//            }
//        }
        List<String> skuIds = adItems.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        return baseMapper.selectBatchIds(skuIds);
    }

    @CacheEvict(cacheNames = "ad-items-skus", key = "#id")
    @Override
    public void delTypeItems(Integer id) {
        baseMapper.deleteById(id);
    }

    @CachePut(cacheNames = "ad-items-skus", key = "#id")
    @Override
    public List<Sku> updateTypeSkuItems(Integer id) {
        List<AdItems> adItems = adItemsMapper.selectList(new QueryWrapper<AdItems>().eq("type", id));
        List<String> skuIds = adItems.stream().map(AdItems::getSkuId).collect(Collectors.toList());
        return baseMapper.selectBatchIds(skuIds);
    }
}
