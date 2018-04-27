package com.spf.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.spf.mapper.*")
@SpringBootApplication(scanBasePackages = "com.spf.*")
@EnableTransactionManagement
public class Boot2Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Boot2Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Boot2Application.class);
    }

}
