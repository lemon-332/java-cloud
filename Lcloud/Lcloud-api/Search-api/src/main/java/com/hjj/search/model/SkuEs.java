package com.hjj.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

@Data
@Document(indexName = "shopsearch", type = "skues")
public class SkuEs {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String name;
    private Integer price;
    private Integer num;
    private String image;
    private String images;
    private Date createTime;
    private Date updateTime;
    private String spuId;

    private Integer categoryId;
    // keyword:不分词
    @Field(type = FieldType.Keyword)
    private String categoryName;
    private Integer brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private String skuAttribute;
    private Integer status;

    // 属性映射
    private Map<String, String> attrMap;
}
