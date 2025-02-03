package com.hjj.good.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category_brand")
public class CategoryBrand implements Serializable {
    private Integer categoryId;
    private Integer brandId;
}
