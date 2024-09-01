package com.openclassrooms.chatop.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Déclare un bean OpenAPI pour configurer la documentation Swagger de l'API
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Configure les informations générales de l'API
                .info(new Info()
                        .title("Chatop API") // Titre de l'API
                        .version("1.0") // Version de l'API
                        .description("API pour l'application Chatop, permettant la gestion des utilisateurs, des messages, et des conversations. "
                                + "Certaines routes nécessitent un token JWT pour y accéder. Utilisez login pour obtenir un token") // Description de l'API
                        .termsOfService("http://chatop.com/terms") // URL des termes de service
                        .contact(new Contact()
                                .name("Support Chatop") // Nom du contact
                                .url("http://chatop.com/support") // URL du support
                                .email("support@chatop.com")) // Email du support
                        .license(new License()
                                .name("Apache 2.0") // Nom de la licence
                                .url("http://springdoc.org"))) // URL de la licence
                // Ajoute une exigence de sécurité pour les routes nécessitant un token JWT
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // Configure les schémas de sécurité
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP) // Type de schéma de sécurité
                                .scheme("bearer") // Schéma de sécurité
                                .bearerFormat("JWT") // Format du token
                                .description("Utilisez un token JWT pour accéder aux endpoints sécurisés. Les routes nécessitant un token sont annotées avec @SecurityRequirement."))); // Description du schéma de sécurité
    }
}