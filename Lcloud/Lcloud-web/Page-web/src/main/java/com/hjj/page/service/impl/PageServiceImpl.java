package com.hjj.page.service.impl;

import com.hjj.page.service.PageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;

@Service
public class PageServiceImpl implements PageService {

    @Value("${pagepath}")
    private String pagepath;

    private final TemplateEngine templateEngine;

    public PageServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void html(String skuId) throws Exception {
        Context context = new Context();
        context.setVariable("skuId", skuId);
        // 生成静态页面
        File file = new File(pagepath, skuId + ".html");

        PrintWriter printWriter = new PrintWriter(file, "UTF-8");

        templateEngine.process("item", context, printWriter);
    }
}
