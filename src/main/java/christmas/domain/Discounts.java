package christmas.domain;

import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.Event;
import christmas.constants.EventBadge;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Discounts {
    private final HashMap<Event, Integer> discounts;

    public Discounts(HashMap<Event, Integer> discounts) {
        HashMap<Event, Integer> benefitDiscounts = filterNoneDiscount(discounts);
        this.discounts = benefitDiscounts;
    }

    private HashMap<Event, Integer> filterNoneDiscount(HashMap<Event, Integer> discounts) {
        HashMap<Event, Integer> benefitDiscounts = new HashMap<>();
        discounts.forEach((event, discount) -> {
            if (discount != ZERO_DISCOUNT) {
                benefitDiscounts.put(event, discount);
            }
        });
        return benefitDiscounts;
    }

    public int getDiscount(Event event) {
        if (discounts.containsKey(event)) {
            return discounts.get(event);
        }
        return ZERO_DISCOUNT;
    }

    public int sumAllBenefitsAmount() {
        return discounts.values().stream().mapToInt(value -> value).sum();
    }

    public int sumAllDiscountAmount() {
        return discounts.entrySet()
                .stream()
                .filter(value -> value.getKey() != Event.GIFT_EVENTS)
                .mapToInt(Entry::getValue)
                .sum();
    }

    public boolean isContainEvent(Event event) {
        return discounts.containsKey(event);
    }

    public boolean isDiscountEmpty() {
        return discounts.isEmpty();
    }

    public Map<Event, Integer> getDiscounts() {
        return Collections.unmodifiableMap(new HashMap<>(discounts));
    }

    public EventBadge createEventBadge() {
        int allBenefitsAmount = sumAllBenefitsAmount();
        if (EventBadge.SANTA.getAmount() <= allBenefitsAmount) {
            return EventBadge.SANTA;
        }
        if (EventBadge.TREE.getAmount() <= allBenefitsAmount) {
            return EventBadge.TREE;
        }
        if (EventBadge.STAR.getAmount() <= allBenefitsAmount) {
            return EventBadge.STAR;
        }
        return EventBadge.NONE;
    }

}
