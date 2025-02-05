package com.hjj.good.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjj.good.model.Brand;
import com.hjj.good.service.BrandService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping()
    public RespResult add(@RequestBody Brand brand) {
        brandService.save(brand);
        return RespResult.ok();
    }

    @PutMapping()
    public RespResult update(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return RespResult.ok();
    }

    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable("id") String id) {
        brandService.removeById(id);
        return RespResult.ok();
    }

    @PostMapping("/search")
    public RespResult<List<Brand>> queryList(@RequestBody Brand brand) {
        List<Brand> brands = brandService.queryList(brand);
        return RespResult.ok(brands);
    }

    @PostMapping("/search/{page}/{size}")
    public RespResult<Page<Brand>> queryPageList(@RequestBody Brand brand,
                                                 @PathVariable("page") Long currentPage,
                                                 @PathVariable("size") Long size) {
        Page<Brand> brandPage = brandService.queryPageList(brand, currentPage, size);
        return RespResult.ok(brandPage);
    }

}
