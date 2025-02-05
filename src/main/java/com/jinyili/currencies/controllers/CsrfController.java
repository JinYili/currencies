package com.jinyili.currencies.controllers;


import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CsrfController {

    @GetMapping("/csrf/token")
    public   CsrfToken csrf(CsrfToken token) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("csrf function not implemented");
       // return token;
    }
}