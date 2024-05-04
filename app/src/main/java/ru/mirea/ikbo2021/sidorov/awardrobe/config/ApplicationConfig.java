package ru.mirea.ikbo2021.sidorov.awardrobe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

/**
 * Класс общей конфигурации
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModules(
                        new ConstraintViolationProblemModule())
                .findAndRegisterModules();
    }
}
