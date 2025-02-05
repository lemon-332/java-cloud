package com.hjj.good.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.good.model.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface BrandMapper extends BaseMapper<Brand> {
    @Select("select brand_id from category_brand where category_id = #{categoryId}")
    List<Integer> selectBrandIds(Integer categoryId);
}
