package com.demoproject.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI demoOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("demo project API")
                        .description("demo sample application")
                        .version("v0.0.1"));
    }
}
