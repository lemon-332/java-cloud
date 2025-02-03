package com.hjj.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.good.model.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> findListByParentId(Integer pid);
}
