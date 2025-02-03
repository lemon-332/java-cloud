package com.hjj.good.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("spu")
public class Spu implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String name;
    private String intro;
    private Integer brandId;
    private Integer categoryOneId;
    private Integer categoryTwoId;
    private Integer categoryThreeId;
    private String images;
    private String afterSaleService;
    private String content;
    private String attributeList;
    private Integer isMarketable;
    private Integer isDelete;
    private Integer status;
}
