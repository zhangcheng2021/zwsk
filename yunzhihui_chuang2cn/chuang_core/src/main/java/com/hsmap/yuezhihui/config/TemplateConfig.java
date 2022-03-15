package com.hsmap.yuezhihui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;

@Component
public class TemplateConfig {
    public final Logger LOGGER = LoggerFactory.getLogger(TemplateConfig.class);

    /*
    不用注册，直接引用
    @Bean
     public TemplateEngine templateEngine() {
         ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
         classLoaderTemplateResolver.setPrefix("classpath:/templates/");
         classLoaderTemplateResolver.setSuffix(".html");
         classLoaderTemplateResolver.setCacheable(false);
         classLoaderTemplateResolver.setCharacterEncoding("utf-8");
         TemplateEngine engine = new TemplateEngine();
         engine.setTemplateResolver(classLoaderTemplateResolver);
         return engine;
     }
 */
    @Autowired
    private TemplateEngine templateEngine;


    /**
     * 生成静态文件
     *
     * @param template 模板名称
     * @param context      数据内容
     * @param file  输出文件
     * @return
     */
    public boolean process(String template, Context context, File file) {
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            //生成HTML
            templateEngine.process(template, context, writer);
        } catch (Exception e) {
            LOGGER.error("静态页方法异常", e);
            return false;
        }
        return true;
    }


}
