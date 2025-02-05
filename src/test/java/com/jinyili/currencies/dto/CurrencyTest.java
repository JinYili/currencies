package com.jinyili.currencies.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {

    private static CurrencyEntity ce;
    @BeforeAll
    public static void setUp(){
        ce = new CurrencyEntity("CNY","518",2,"Renminbi",true);
    }

    @Test
    void testCurrencyConstructor(){

        assertAll(  () -> {
            assertEquals("CNY" ,ce.getCode());
            assertEquals(  "Renminbi",ce.getName());
        });

    }

}
