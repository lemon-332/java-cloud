package com.hjj.good.controller;

import com.hjj.good.model.Category;
import com.hjj.good.service.CategoryService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/parent/{id}")
    public RespResult<List<Category>> getCategoryListByPid(@PathVariable Integer id) {
        List<Category> listByParentId = categoryService.findListByParentId(id);
        return RespResult.ok(listByParentId);
    }
}
