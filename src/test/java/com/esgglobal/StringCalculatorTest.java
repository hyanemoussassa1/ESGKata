package com.esgglobal;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"0,10,30,10", "10,10,10,10,10"})
    void testThatAddCanHandleUnknowNumberOfNumbers(String input){
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(50, count);
    }

    @Test
    void testThatAddCanHandleNewLineAsDelimiter(){
        String input = "1\n2,3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(6, count);
    }

}
