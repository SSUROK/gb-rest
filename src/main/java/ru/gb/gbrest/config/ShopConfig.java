package ru.gb.gbrest.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.gb.gbrest.service.feign.ManufacturerDtoApi;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditAwareBean")
@EnableFeignClients(basePackageClasses = {ManufacturerDtoApi.class})
public class ShopConfig {

    @Bean
    public AuditorAware<String> auditAwareBean() {
        return () -> Optional.of("User");
    }
}
