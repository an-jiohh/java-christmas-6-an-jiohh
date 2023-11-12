package christmas.validation;

import static christmas.constants.Constants.DECEMBER_END_DATE;
import static christmas.constants.Constants.DECEMBER_START_DATE;

import christmas.exception.DateRangeException;

public class DateValidator {
    public static void validateDate(int date) {
        checkDateRange(date);
    }

    private static void checkDateRange(int date) {
        if (DECEMBER_START_DATE > date || DECEMBER_END_DATE < date) {
            throw DateRangeException.exception;
        }
    }
}