package com.silence.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author silence
 * @since 2024/9/11 11:36
 **/
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI createOpenApi() {
        return new OpenAPI()
                .info(new Info().title("My-Project API 文档")
                        .contact(new Contact())
                        .description("My-Project API 文档")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
