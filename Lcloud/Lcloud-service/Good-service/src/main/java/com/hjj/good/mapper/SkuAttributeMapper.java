package com.hjj.good.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.good.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {
    @Select("select * from sku_attribute where id in(select attr_id from category_attr where category_id=#{categoryId})")
    public List<SkuAttribute> selectByCategoryId(Integer categoryId);
}
