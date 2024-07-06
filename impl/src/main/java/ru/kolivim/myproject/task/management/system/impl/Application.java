package ru.kolivim.myproject.task.management.system.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepositoryImpl;

@EnableCaching
@EnableScheduling
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EntityScan("ru.kolivim.myproject.task.management.system.domain.*")
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
