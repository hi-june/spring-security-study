package com.demoproject.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo()) // API Doc 정보 매핑
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demoproject.demo"))  // base 패키지 설정
                .paths(PathSelectors.any()) // base 패키지 전부
                //.paths(PathSelectors.ant("/v1/**"))  // base 패키지 내 v1만 선택할수도 있다.
                .build()
                .useDefaultResponseMessages(false); // 기본 세팅값인 200, 401, 402, 403, 404를 사용하지 않는다.
    }

    private ApiInfo swaggerInfo() { // API Doc 정보
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("demo 프로젝트의 API 설명을 위한 문서입니다.")
                .license("hi-june")
                .licenseUrl("hi-june.github.io")
                .version("1")
                .build();
    }
}
