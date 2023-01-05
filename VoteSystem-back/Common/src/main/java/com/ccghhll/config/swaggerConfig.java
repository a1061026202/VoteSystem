package com.ccghhll.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class swaggerConfig {

    @Bean
    public Docket AdminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Api")
                .apiInfo(AdminDesc())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    private ApiInfo AdminDesc(){
        return new ApiInfoBuilder()
                .title("投票系统接口文档")
                .description("文档描述了投票接口的调用模块")
                .contact(new Contact("ccghhl","http://159.75.118.217","ccghhl@qq.com"))
                .version("1.0").build();
    }



}
