package christmas.domain;

import christmas.constants.DecemberEvent;
import java.util.List;

public class TodayBenefits {
    private final List<DecemberEvent> benefits;

    public TodayBenefits(List<DecemberEvent> events) {
        this.benefits = events;
    }

    public boolean isContainEvents(DecemberEvent event) {
        return benefits.contains(event);
    }
}
