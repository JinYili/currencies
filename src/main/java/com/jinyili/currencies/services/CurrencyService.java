package com.jinyili.currencies.services;

import com.jinyili.currencies.dto.CurrencyEntity;
import com.jinyili.currencies.dto.RateEntity;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

public class CurrencyService {

    private final String url ;
    private final HttpEntity<String> httpEntity;
    private final RestTemplate restTemplate;


    public CurrencyService(Environment env) {
        this.url = env.getProperty("spring.application.url");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","ApiKey "+ env.getProperty("spring.application.key"));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        this.httpEntity = new HttpEntity<String>(headers);
        this.restTemplate    = new RestTemplate();
    }

    private String getUrl(String str){
        return  String.format("%s%s",this.url,str) ;
    }

    @Cacheable("LISTCURRENCIES")
    public  List<CurrencyEntity> getCurrencies(){
        ResponseEntity<List<CurrencyEntity>> response = this.restTemplate.exchange(getUrl("currencies") ,
                HttpMethod.GET, this.httpEntity, new ParameterizedTypeReference<List<CurrencyEntity>>(){});
        return response.getBody();
    }

    public RateEntity getRate(String base,String quote) {
        String str = String.format("rates/%s/%s",base,quote);
        ResponseEntity<RateEntity> response = this.restTemplate.exchange(getUrl(str) ,
                HttpMethod.GET,this.httpEntity, RateEntity.class);
        return response.getBody();
    }

}
