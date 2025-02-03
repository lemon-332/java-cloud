package com.hjj.good.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.good.model.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {
    List<Brand> queryList(Brand brand);

    Page<Brand> queryPageList(Brand brand, Long currentPage,Long size);
}
