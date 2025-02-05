package com.jinyili.currencies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CurrencyEntity {

    public CurrencyEntity(String code, String numeric_code, int decimal_digits, String name, Boolean active) {
        this.code = code;
        this.numeric_code = numeric_code;
        this.decimal_digits = decimal_digits;
        this.name = name;
        this.active = active;
    }

    private String code;
    private String numeric_code;
    private int decimal_digits;
    private  String name;
    private  Boolean active;

    public String getCode() {
        return code;
    }

    public String getNumeric_code() {
        return numeric_code;
    }

    public int getDecimal_digits() {
        return decimal_digits;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }
}
