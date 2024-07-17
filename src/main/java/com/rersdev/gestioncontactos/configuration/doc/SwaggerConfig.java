package com.rersdev.gestioncontactos.configuration.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "API CONTACTS",
                description = "Contact management",
                termsOfService = "Practice application",
                version = "1.0.0",
                contact = @Contact(
                        name = "Rodys Rodriguez",
                        url = "https://www.linkedin.com/in/rodys-rodriguez/",
                        email = "rodisenrique73@gmail.com"
                )
        ),
        servers = @Server(
                description = "SERVER",
                url = "http://localhost:9090"
        )
)
public class SwaggerConfig {

@Bean
    public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi.builder()
            .group("spring-shop")
            .pathsToMatch("/api/**")
            .build();
}
}
