package com.example.bibliotheque.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod") // Désactive en production
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API Bibliothèque")
                    .version("1.0")
                    .description("Documentation de l'API de gestion de bibliothèque")
                    .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation()
                    .description("Wiki Technique")
                    .url("https://github.com/bibliotheque-api/docs"));
    }

    @Bean
    public GroupedOpenApi livresApi() {
        return GroupedOpenApi.builder()
                .group("livres")
                .displayName("Gestion des Livres")
                .pathsToMatch("/api/livres/**")
                .build();
    }

    @Bean
    public GroupedOpenApi empruntsApi() {
        return GroupedOpenApi.builder()
                .group("emprunts")
                .displayName("Gestion des Emprunts")
                .pathsToMatch("/api/emprunts/**")
                .build();
    }
}
