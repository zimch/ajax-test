package ru.zimch.ajaxtest.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Конфигурация для работы со статикой
 */
@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("classpath:/static/js/");
    }
}