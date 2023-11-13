package christmas.domain;

import christmas.constants.Week;
import christmas.validation.DateValidator;
import java.util.List;

public class Date {
    private final int date;

    public Date(int date) {
        DateValidator.validateDate(date);
        this.date = date;
    }

    public boolean isContainWeeks(List<Week> targetWeek) {
        Week weekOfDate = getWeek();
        return targetWeek.contains(weekOfDate);
    }

    public boolean isContainDays(List<Integer> targetDays) {
        return targetDays.contains(date);
    }

    public Week getWeek() {
        Week[] values = Week.values();
        return values[date % 7];
    }

    public int getDate() {
        return date;
    }
}
