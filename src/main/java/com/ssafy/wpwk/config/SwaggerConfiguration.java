package com.ssafy.wpwk.config;


import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


//http://localhost:8080/swagger-ui.html#

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                groupName("public-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.wpwk.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("WPWK API")
                .description("<h3>With Parents With Kids </h3>Swagger를 이용한 WPWK API")
                .contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp").version("1.0").build();
    }

}
