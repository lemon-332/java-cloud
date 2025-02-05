package com.hjj.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.good.mapper.BrandMapper;
import com.hjj.good.model.Brand;
import com.hjj.good.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public List<Brand> queryList(Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", brand.getName());
        queryWrapper.eq("initial", brand.getInitial());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Brand> queryPageList(Brand brand, Long currentPage, Long size) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", brand.getName());
        return baseMapper.selectPage(new Page<>(currentPage, size), queryWrapper);
    }

    @Override
    public List<Brand> queryByCategoryId(Integer categoryId) {
        List<Integer> brandIds = baseMapper.selectBrandIds(categoryId);
        if (brandIds != null || brandIds.size() > 0) {
            return baseMapper.selectList(new QueryWrapper<Brand>().in("id", brandIds));
        }
        return null;
    }
}
