package com.hjj.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.good.model.Product;
import com.hjj.good.model.Spu;

public interface SpuService extends IService<Spu> {
    void saveProduct(Product product);
}
