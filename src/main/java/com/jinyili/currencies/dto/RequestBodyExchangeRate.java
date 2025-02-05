package com.jinyili.currencies.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties
public class RequestBodyExchangeRate {
    private String base;
    private  String quote;

    public void setBase(String base) {
        this.base = base.trim();
    }

    public void setQuote(String quote) {
        this.quote = quote.trim();
    }

    public RequestBodyExchangeRate(String base, String quote) {
        this.base = base;
        this.quote = quote;
    }
}
