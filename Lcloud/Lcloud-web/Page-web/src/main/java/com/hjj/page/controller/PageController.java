package com.hjj.page.controller;

import com.hjj.page.service.PageService;
import com.hjj.util.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{id}")
    public RespResult html(@PathVariable("id") String id) throws Exception {
        pageService.html(id);
        return RespResult.ok();
    }
}
