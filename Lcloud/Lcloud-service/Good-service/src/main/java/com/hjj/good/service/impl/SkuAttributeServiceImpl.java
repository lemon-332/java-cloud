package com.hjj.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.good.mapper.SkuAttributeMapper;
import com.hjj.good.model.SkuAttribute;
import com.hjj.good.service.SkuAttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {
    @Override
    public List<SkuAttribute> queryList(Integer categoryId) {
        return baseMapper.selectByCategoryId(categoryId);
    }
}
