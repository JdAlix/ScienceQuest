package fr.iut.sciencequest.sae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SaeApplication {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			@NonNull
			public void addCorsMappings(@Nullable CorsRegistry registry) {
                assert registry != null;
                registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(SaeApplication.class, args);
	}
}
