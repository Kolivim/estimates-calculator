package ru.kolivim.estimates.calculator.impl.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.*;

@Configuration
@EnableWebMvc
public class CorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.setAllowedHeaders(Arrays.asList(
                AUTHORIZATION, CONTENT_TYPE, ACCEPT));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),HttpMethod.PUT.name(), HttpMethod.DELETE.name(),
                HttpMethod.POST.name()));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;
    }
}