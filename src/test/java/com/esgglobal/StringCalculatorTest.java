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

    @Test
    void testThatInputCanSpecifyTheDelimiterSemicolon(){
        String input = "//;\n1;2";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(3, count);
    }

    @Test
    void testThatInputCanSpecifyTheDelimiterLine(){
        String input = "//|\n1|2";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(3, count);
    }

    @Test
    void testThatInputContainingNegativeNumbersThrowsException(){
        String input = "-1,2";
        StringCalculator stringCalculator = new StringCalculator();
        InputValidationException thrown = Assertions.assertThrows(
                InputValidationException.class,
                () ->  stringCalculator.add(input),
                "Didnt throw an exception");
        Assertions.assertEquals("Negatives not allowed: -1", thrown.getMessage());
    }

    @Test
    void testThatInputContainingNegativeNumbersThrowsExceptionWithCorrectMessage(){
        String input = "2,-4,3,-5";
        StringCalculator stringCalculator = new StringCalculator();
        InputValidationException thrown = Assertions.assertThrows(
                InputValidationException.class,
                () ->  stringCalculator.add(input),
                "Didnt throw an exception");
        Assertions.assertEquals("Negatives not allowed: -4,-5", thrown.getMessage());
    }

    @Test
    void testThatNumbersGreaterThan1000AreIgnored(){
        String input = "1001,2";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(2, count);
    }

    @Test
    void testThat1000IsNotIgnored(){
        String input = "1000,3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(1003, count);
    }

    @Test
    void testThatNumbersGreaterThan1000AreIgnoredWithDifferentDelimiter(){
        String input = "//;\n1001;2";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(2, count);
    }

    @Test
    void testThatDelimiterCanBeOfLengthThree(){
        String input = "//[|||]\n1|||2|||3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(6, count);
    }

    @Test
    void testThatDelimiterCanBeOfLengthFour(){
        String input = "//[;;;;]\n1;;;;2;;;;3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(6, count);
    }

    @Test
    void testThatMultipleDelimitersAreAllowed(){
        String input = "//[|][%]\n1|2%3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(6, count);
    }

    @Test
    void testThatMultipleDelimitersOfAnyLengthAreAllowed(){
        String input = "//[|||][%][%%]\n10|||2%%3";
        StringCalculator stringCalculator = new StringCalculator();
        int count = stringCalculator.add(input);
        Assertions.assertEquals(15, count);
    }



}
