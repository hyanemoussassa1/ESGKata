package com.esgglobal;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.length() == 0){
            return 0;
        }
        return Arrays.asList(numbers.split("\n|,"))
                .stream()
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
    }
}
