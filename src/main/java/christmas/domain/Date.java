package christmas.domain;

import christmas.validation.DateValidator;

public class Date {
    private final int date;

    public Date(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        DateValidator.validateDate(date);
    }
}
