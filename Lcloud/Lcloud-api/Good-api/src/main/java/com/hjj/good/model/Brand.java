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
@TableName("brand")
public class Brand implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String image;
    private String initial;
    private Integer sort;
}
