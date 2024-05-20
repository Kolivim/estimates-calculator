package ru.kolivim.estimates.calculator.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@EntityScan("ru/kolivim/estimates/calculator/domain.*")
@SpringBootApplication/*(exclude =  {DataSourceAutoConfiguration.class })*/
//@PropertySource("classpath:impl/src/main/resources/application.yml")
/*@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)*/
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


//@SpringBootApplication
//@EntityScan/*"domain.*")*/("ru/kolivim/estimates/calculator/domain.*")
//public class Application {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//}
