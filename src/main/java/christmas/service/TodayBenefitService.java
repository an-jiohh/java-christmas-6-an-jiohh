package christmas.service;

import christmas.constants.DecemberEvent;
import christmas.domain.Date;
import christmas.domain.TodayBenefits;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodayBenefitService {

    private final Date date;

    public TodayBenefitService(Date date) {
        this.date = date;
    }

    public TodayBenefits getTodayEvents() {
        List<DecemberEvent> events = checkTodayEvent();
        return new TodayBenefits(events);
    }

    private List<DecemberEvent> checkTodayEvent() {
        DecemberEvent[] values = DecemberEvent.values();
        List<DecemberEvent> todayEvent = new ArrayList<>();
        Arrays.stream(values).forEach((event -> {
            if (checkEvent(event)) {
                todayEvent.add(event);
            }
        }));
        return todayEvent;
    }

    private boolean checkEvent(DecemberEvent event) {
        if (date.isContainDays(event.getTargetDay())) {
            return true;
        }
        if (date.isContainWeek(event.getTargetWeek())) {
            return true;
        }
        return false;
    }
}
