package com.jinyili.currencies.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateTest {

    private static RateEntity re;

    @BeforeAll
    public static void setUp(){
        re = new RateEntity("CNY","USD",0.15f,"today");
    }

    @Test
    void testRateConstructor(){

        assertAll(  () -> {
            assertEquals("CNY",re.getBase_currency() );
            assertEquals( "USD",re.getQuote_currency());
            assertEquals( 0.15f, re.getQuote());
        });

    }
}
