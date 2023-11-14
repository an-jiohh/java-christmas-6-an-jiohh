package christmas.domain;

import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.DecemberEvent;
import java.util.HashMap;

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

}
