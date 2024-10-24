package fr.lgrb.back_project.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Autorise les requêtes sur les endpoints api
                .allowedOrigins("http://localhost:4200") // Autorise les requêtes venant de ton frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Tous les headers sont autorisés
                .allowCredentials(true); // Autorise l'envoi de cookies
    }
}