package com.hjj.good.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.good.mapper.BrandMapper;
import com.hjj.good.mapper.CategoryMapper;
import com.hjj.good.mapper.SkuMapper;
import com.hjj.good.mapper.SpuMapper;
import com.hjj.good.model.Brand;
import com.hjj.good.model.Category;
import com.hjj.good.model.Product;
import com.hjj.good.model.Spu;
import com.hjj.good.service.SpuService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {


    private final SkuMapper skuMapper;

    private final CategoryMapper categoryMapper;

    private final BrandMapper brandMapper;

    public SpuServiceImpl(SkuMapper skuMapper, CategoryMapper categoryMapper, BrandMapper brandMapper) {
        this.skuMapper = skuMapper;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
    }

    @Override
    public void saveProduct(Product product) {
        Spu spu = product.getSpu();
        spu.setIsMarketable(1);// 已上架
        spu.setIsDelete(0);// 未删除
        spu.setStatus(1);// 已审核
        baseMapper.insert(spu);
        Date date = new Date();
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        Brand brand = brandMapper.selectById(spu.getBrandId());
        product.getSkus().forEach(sku -> {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setCategoryId(spu.getCategoryThreeId());
            sku.setStatus(1);
            sku.setBrandId(spu.getBrandId());
            sku.setBrandName(brand.getName());
            sku.setCategoryName(category.getName());
            String name = spu.getName();
            Map<String, String> skuAttrMap = JSON.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> entry : skuAttrMap.entrySet()) {
                name += " " + entry.getValue();
            }
            sku.setName(name);
            skuMapper.insert(sku);
        });
    }
}
