package io.c12.bala.c12app.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "user", description = "user crud operation")
        },
        externalDocs = @ExternalDocumentation(
                description = "location of source code for this api",
                url = "https://github.nwie.net/palanib2/c12-app"
        ),
        info = @Info(
                title = "Springboot User API",
                version = "0.1.0",
                contact = @Contact(
                        name = "palanib2",
                        email = "b.palaniappan@nationwide.com"
                ),
                license = @License(
                        name = "MIT",
                        url = "http://localhost/"
                )
        ),
        security = @SecurityRequirement(name = "no_auth")
)
public class OpenApiConfig {
}
