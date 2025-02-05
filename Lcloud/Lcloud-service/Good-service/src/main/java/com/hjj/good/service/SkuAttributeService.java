package com.hjj.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.good.model.SkuAttribute;

import java.util.List;

public interface SkuAttributeService extends IService<SkuAttribute> {
    List<SkuAttribute> queryList(Integer categoryId);
}
