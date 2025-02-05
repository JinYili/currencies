package com.jinyili.currencies.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class RateEntity {
    private String base_currency;
    private String quote_currency;
    private Float quote;
    private String date;

    public RateEntity(String base_currency, String quote_currency, Float quote, String date) {
        this.base_currency = base_currency;
        this.quote_currency = quote_currency;
        this.quote = quote;
        this.date = date;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public Float getQuote() {
        return quote;
    }

    public String getDate() {
        return date;
    }

}
