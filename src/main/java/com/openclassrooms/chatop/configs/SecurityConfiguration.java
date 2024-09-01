package com.openclassrooms.chatop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructeur pour injecter les dépendances nécessaires
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Configure la chaîne de filtres de sécurité
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() // Désactive la protection CSRF
                .cors().configurationSource(corsConfigurationSource()).and() // Configure CORS
                .authorizeHttpRequests()
                // Spécifie les chemins d'URL pour lesquels l'accès est autorisé sans authentification
                .requestMatchers("/api/auth/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/api/static/upload/**")
                .permitAll() // Autorise l'accès sans authentification à ces endpoints        
                // Spécifie les chemins d'URL pour lesquels l'accès nécessite une authentification
                .requestMatchers("/api/auth/me", "/api/rentals/**", "/api/messages/**")
                .authenticated() // Nécessite une authentification pour accéder à ces endpoints
                .anyRequest()
                .authenticated() // Nécessite une authentification pour toutes les autres requêtes
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Utilise des sessions sans état
                .and()
                .authenticationProvider(authenticationProvider) // Utilise le fournisseur d'authentification personnalisé
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Ajoute le filtre JWT avant le filtre d'authentification par nom d'utilisateur et mot de passe

        return http.build();
    }

    // Configure les paramètres CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8005", "http://localhost:4200")); // Autorise ces origines
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Autorise ces méthodes HTTP
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Autorise ces headers
        configuration.setAllowCredentials(true); // Autorise l'envoi de cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Applique cette configuration à toutes les routes

        return source;
    }
}
