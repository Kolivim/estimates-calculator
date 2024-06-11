package ru.kolivim.estimates.calculator.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import ru.kolivim.estimates.calculator.impl.swing.form.RegistrationForm;


@EntityScan("ru/kolivim/estimates/calculator/domain.*")
@SpringBootApplication/*(exclude =  {DataSourceAutoConfiguration.class })*/
//@PropertySource("classpath:impl/src/main/resources/application.yml")
/*@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)*/
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        /** TODO: Вынести в стартовый класс - инициализатор форм */
        //
        System.out.println("Application main");
//        RegistrationForm registrationForm = new RegistrationForm();
//        registrationForm.create();
        //

    }


    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            System.out.println("In CommandLineRunnerImpl ");

            for (String arg : args) {
                System.out.println(arg);
            }
        };
    }
}


//@SpringBootApplication
//@EntityScan/*"domain.*")*/("ru/kolivim/estimates/calculator/domain.*")
//public class Application {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//}
