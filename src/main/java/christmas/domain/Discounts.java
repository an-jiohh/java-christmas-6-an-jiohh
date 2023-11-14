package christmas.domain;

import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.DecemberEvent;
import christmas.constants.EventBadge;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Discounts {
    private final HashMap<DecemberEvent, Integer> discounts;

    public Discounts(HashMap<DecemberEvent, Integer> discounts) {
        HashMap<DecemberEvent, Integer> benefitDiscounts = filterNoneDiscount(discounts);
        this.discounts = benefitDiscounts;
    }

    private HashMap<DecemberEvent, Integer> filterNoneDiscount(HashMap<DecemberEvent, Integer> discounts) {
        HashMap<DecemberEvent, Integer> benefitDiscounts = new HashMap<>();
        discounts.forEach((event, discount) -> {
            if (discount != ZERO_DISCOUNT) {
                benefitDiscounts.put(event, discount);
            }
        });
        return benefitDiscounts;
    }

    public int getDiscount(DecemberEvent event) {
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
                .filter(value -> value.getKey() != DecemberEvent.GIFT_EVENTS)
                .mapToInt(Entry::getValue)
                .sum();
    }

    public boolean isContainEvent(DecemberEvent decemberEvent) {
        return discounts.containsKey(decemberEvent);
    }

    public boolean isDiscountEmpty() {
        return discounts.isEmpty();
    }

    public Map<DecemberEvent, Integer> getDiscounts() {
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
