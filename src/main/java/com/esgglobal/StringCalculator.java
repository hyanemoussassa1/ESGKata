package com.esgglobal;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        String delimiterRegex = "\n|,";
        String numbersStr = numbers;
        if (numbers == null || numbers.length() == 0){
            return 0;
        }

        if (numbers.startsWith("//")){
            int indexOfDelimiterNumberDivider = numbers.indexOf("\n");
            delimiterRegex = numbers
                    .substring(0, indexOfDelimiterNumberDivider)
                    .replaceFirst("//", "");
            numbersStr = numbers.substring(indexOfDelimiterNumberDivider + 1);
        }

        return Arrays.asList(numbersStr.split(delimiterRegex))
                .stream()
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
    }
}
