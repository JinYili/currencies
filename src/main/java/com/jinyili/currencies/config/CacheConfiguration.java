package com.jinyili.currencies.config;


import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {


    public static final String NAME = "LISTCURRENCIES";

    @Bean
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager(NAME);
    }

    @Bean
    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
        return new ConcurrentCustomizer();
    }

    class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setAllowNullValues(false);
            System.out.println("Cache invoked!!");

        }

        @CacheEvict(allEntries = true, value =NAME)
        @Scheduled(fixedDelay = 10 * 60 * 1000 ,  initialDelay = 500)
        public void reportCacheEvict() {
            System.out.println("Flush Cache " + new Date().toString());
        }
    }

}
