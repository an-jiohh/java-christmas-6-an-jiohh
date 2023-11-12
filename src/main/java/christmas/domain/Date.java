package christmas.domain;

import christmas.validation.DateValidator;

public class Date {
    private final int date;

    public Date(int date) {
        DateValidator.validateDate(date);
        this.date = date;
    }
    
}
