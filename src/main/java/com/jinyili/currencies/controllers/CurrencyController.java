package com.jinyili.currencies.controllers;

import com.jinyili.currencies.dto.CurrencyEntity;
import com.jinyili.currencies.dto.RateEntity;
import com.jinyili.currencies.dto.RequestBodyExchangeRate;
import com.jinyili.currencies.services.CurrencyService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {


    @Autowired
    private Environment env;
    private CurrencyService currencyService;

    @PostConstruct
    public void CurrenciesApplicationPost(){
        currencyService = new CurrencyService(env);
    }

    @GetMapping("/getCurrencyList")
    public ResponseEntity<List<CurrencyEntity>> getCurrencyList(){
        List<CurrencyEntity> currencies = currencyService.getCurrencies();
        return ResponseEntity.status(HttpStatus.OK).body(currencies);
    }

    @PostMapping ("/getRate")
    public ResponseEntity<RateEntity> getRate(@RequestBody RequestBodyExchangeRate params) {

        if(  Objects.equals(params.getBase(), null) ||  Objects.equals(params.getQuote(), null) || Objects.equals(params.getBase(), "") || Objects.equals(params.getQuote(), "")){
            throw new HttpMessageNotReadableException("Missing base or quote currency");
        }

        RateEntity rate = currencyService.getRate(params.getBase(),params.getQuote());
        return ResponseEntity.status(HttpStatus.OK).body(rate);
    }

}
