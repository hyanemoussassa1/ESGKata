package com.esgglobal;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    void testThatEmptyStringReturnsZero(){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add("");
        Assertions.assertEquals(0, count);
    }

    @Test
    void testThatNullStringReturnsZero(){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(null);
        Assertions.assertEquals(0, count);
    }

    @Test
    void testThatStringContainingOneReturnsOne(){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add("1");
        Assertions.assertEquals(1, count);
    }
    @Test
    void testThatStringContainingOneCommaTwoReturnsThree(){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add("1,2");
        Assertions.assertEquals(3, count);
    }

    @Test
    void testThatAddCanHandleFiveNumbers(){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add("10,10,10,10,10");
        Assertions.assertEquals(50, count);
    }

}
