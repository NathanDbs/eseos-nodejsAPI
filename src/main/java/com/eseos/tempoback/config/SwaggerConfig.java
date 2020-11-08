package com.eseos.tempoback.config;

import io.swagger.annotations.*;
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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * The API information
     * @return the API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TEMPO-BACK")
                .description("Documentation de l'API du club ESE'OS")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("Nathan Dubois", "", "nathan.dubois@reseau.eseo.fr"))
                .build();
    }

    /**
     * Swagger configuration
     * @return the swagger configuration
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eseos.tempoback"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

}
