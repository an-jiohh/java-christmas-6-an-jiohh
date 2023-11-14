package christmas.domain;

import christmas.constants.Event;
import java.util.List;

public class TodayBenefits {
    private final List<Event> benefits;

    public TodayBenefits(List<Event> events) {
        this.benefits = events;
    }

    public boolean isContainEvents(Event event) {
        return benefits.contains(event);
    }
}
