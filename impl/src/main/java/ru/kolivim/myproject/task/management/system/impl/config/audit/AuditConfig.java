package ru.kolivim.myproject.task.management.system.impl.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.kolivim.myproject.task.management.system.domain.base.audit.UserJsonType;
import ru.kolivim.myproject.task.management.system.impl.utils.audit.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    public AuditorAware<UserJsonType> auditorAware(){
        return new AuditorAwareImpl();
    }
}
