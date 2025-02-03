package com.hjj.good.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sku")
public class Sku implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private Integer spuId;
    private String name;
    private Integer price;
    private Integer num;
    private String image;
    private String images;
    private Date createTime;
    private Date updateTime;
    private Integer categoryId;
    private String categoryName;
    private String brandName;
    private Integer brandId;
    private String skuAttribute;
    private Integer status;
}
