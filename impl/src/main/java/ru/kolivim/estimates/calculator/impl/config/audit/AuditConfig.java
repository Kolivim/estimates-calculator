package ru.kolivim.estimates.calculator.impl.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.kolivim.estimates.calculator.impl.utils.audit.AuditorAwareImpl;
import ru.kolivim.estimates.calculator.domain.base.audit.UserJsonType;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    public AuditorAware<UserJsonType> auditorAware(){
        return new AuditorAwareImpl();
    }
}
