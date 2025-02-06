package com.hjj.good.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sku")
@Table
public class Sku implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    @Column(name = "spu_id")
    private Integer spuId;
    private String name;
    private Integer price;
    private Integer num;
    private String image;
    private String images;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "sku_attribute")
    private String skuAttribute;
    private Integer status;
}
