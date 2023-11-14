package christmas.service;

import christmas.constants.Event;
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
        List<Event> events = checkTodayEvent();
        return new TodayBenefits(events);
    }

    private List<Event> checkTodayEvent() {
        Event[] values = Event.values();
        List<Event> todayEvent = new ArrayList<>();
        Arrays.stream(values).forEach((event -> {
            if (checkEvent(event)) {
                todayEvent.add(event);
            }
        }));
        return todayEvent;
    }

    private boolean checkEvent(Event event) {
        if (date.isContainDays(event.getTargetDay())) {
            return true;
        }
        if (date.isContainWeeks(event.getTargetWeek())) {
            return true;
        }
        return false;
    }
}
