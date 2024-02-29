package fr.iut.sciencequest.sae;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.FixedContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    // Permet de forcer l'affichage des erreurs en JSON sans prendre en compte les headers du client
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.strategies(List.of(new FixedContentNegotiationStrategy(MediaType.APPLICATION_JSON)));
    }

}