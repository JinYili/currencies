package com.jinyili.currencies.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class WebConfig {

    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;


        @Bean
        public FilterRegistrationBean corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("http://localhost:3000");
            config.setAllowedHeaders(Arrays.asList(
                    HttpHeaders.AUTHORIZATION,
                    HttpHeaders.CONTENT_TYPE,
                    "X-XSRF-TOKEN",
                    HttpHeaders.ACCEPT));
            config.setAllowedMethods(Arrays.asList(
                    HttpMethod.GET.name(),
                    HttpMethod.POST.name(),
                    HttpMethod.PUT.name(),
                    HttpMethod.OPTIONS.name(),
                    HttpMethod.DELETE.name()));
            config.setMaxAge(MAX_AGE);
            source.registerCorsConfiguration("/**", config);
            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

            bean.setOrder(CORS_FILTER_ORDER);
            return bean;
        };


    }

