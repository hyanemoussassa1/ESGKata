package com.esgglobal;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringCalculator {

    public static final String PRE_DELIMITER_DOUBLE_FORWARD_SLASH = "//";
    public static final String DELIMITER_REGEX_NEW_LINE_OR_COMMA = "\n|,";
    public static final String LINE_BREAK = "\n";
    public static final String EMPTY_STRING = "";

    public int add(String input) {
        String numbersWithDelimiter;
        String delimiterRegex;
        Predicate<Integer> integersLessOrEqual1000 = (number) -> number <= 1000;

        if (input == null || input.isEmpty())
            return 0;

        if (input.startsWith(PRE_DELIMITER_DOUBLE_FORWARD_SLASH)){
            int indexOfDelimiterNumberDivider = input.indexOf(LINE_BREAK);
            delimiterRegex = input
                    .substring(0, indexOfDelimiterNumberDivider)
                    .replaceFirst(PRE_DELIMITER_DOUBLE_FORWARD_SLASH, EMPTY_STRING);
            numbersWithDelimiter = input.substring(indexOfDelimiterNumberDivider + 1);
        } else {
            numbersWithDelimiter = input;
            delimiterRegex = DELIMITER_REGEX_NEW_LINE_OR_COMMA;
        }
        List<Integer> numbers = Arrays
                .stream(numbersWithDelimiter.split(delimiterRegex))
                .map(Integer::valueOf)
                .toList();
        throwExceptionForNegativeNumbersInList(numbers);
        return numbers.stream()
                .filter(integersLessOrEqual1000)
                .reduce(0, Integer::sum);
    }

    private static void throwExceptionForNegativeNumbersInList(List<Integer> numberList) {
        String negativeNumbers = numberList.stream()
                .filter(entry -> entry < 0)
                .map(Objects::toString)
                .collect(Collectors.joining(","));
        if (!negativeNumbers.isEmpty()) {
            throw new InputValidationException("Negatives not allowed: " + negativeNumbers);
        }
    }
}
