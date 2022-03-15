package com.hsmap.yuezhihui.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.hsmap.yuezhihui.mapper.**"})
@ComponentScan("com.hsmap.yuezhihui.**")
public class EduApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EduApiApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(EduApiApplication.class);
    }
}
