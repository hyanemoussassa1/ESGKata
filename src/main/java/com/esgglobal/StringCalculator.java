package com.esgglobal;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public static final String PRE_DELIMITER_DOUBLE_FORWARD_SLASH = "//";
    public static final String DELIMITER_REGEX_NEW_LINE_OR_COMMA = "\n|,";
    public static final String LINE_BREAK = "\n";
    public static final String EMPTY_STRING = "";
    public static final String REGEX_OR_OPERATOR = "|";
    public static final String COMMA = ",";

    public int add(String input) {
        Predicate<Integer> IntegerLessThan1000 = (number) -> number <= 1000;

        if (input == null || input.isEmpty())
            return 0;

        String numbersWithDelimiter = getNumbersWithDelimiter(input);
        String delimiterRegex = getDelimiterRegex(input);
        List<Integer> numbers = Arrays
                .stream(numbersWithDelimiter.split(delimiterRegex))
                .map(Integer::valueOf)
                .toList();
        throwExceptionForNegativeNumbersInList(numbers);
        return numbers.stream()
                .filter(IntegerLessThan1000)
                .reduce(0, Integer::sum);
    }

    private static String getNumbersWithDelimiter(String input) {
        if (delimiterIsNotSet(input)){
            return input;
        }
        int indexOfDelimiterAndNumberDivider = input.indexOf(LINE_BREAK);
        return input.substring(indexOfDelimiterAndNumberDivider + 1);
    }

    private static boolean delimiterIsNotSet(String input) {
        return ! input.startsWith(PRE_DELIMITER_DOUBLE_FORWARD_SLASH);
    }

    private static String getDelimiterRegex(String input) {
        if (delimiterIsNotSet(input)){
            return DELIMITER_REGEX_NEW_LINE_OR_COMMA;
        }
        Set<String> delimitersOfMultipleLength = getDelimitersOfMultipleLength(input);
        return delimitersOfMultipleLength.isEmpty() ?
                getDelimiterOfSingleLength(input) :
                delimitersOfMultipleLength.stream()
                        .map(pt -> Pattern.quote(pt))
                        .collect(Collectors.joining(REGEX_OR_OPERATOR));
    }

    private static String getDelimiterOfSingleLength(String input) {
        return Pattern.quote(input
                .substring(0, input.indexOf(LINE_BREAK))
                .replaceFirst(PRE_DELIMITER_DOUBLE_FORWARD_SLASH, EMPTY_STRING));
    }

    private static Set<String> getDelimitersOfMultipleLength(String input) {
        Set<String> delimiters = new HashSet<>();
        Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(input);
        while (matcher.find()) {
            delimiters.add(matcher.group(1));
        }
        return delimiters;
    }

    private static void throwExceptionForNegativeNumbersInList(List<Integer> numberList) {
        String negativeNumbers = numberList.stream()
                .filter(entry -> entry < 0)
                .map(Objects::toString)
                .collect(Collectors.joining(COMMA));
        if (!negativeNumbers.isEmpty()) {
            throw new InputValidationException("Negatives not allowed: " + negativeNumbers);
        }
    }
}
