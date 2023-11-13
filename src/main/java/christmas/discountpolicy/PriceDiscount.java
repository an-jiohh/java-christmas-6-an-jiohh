package christmas.discountpolicy;

import static christmas.constants.Constants.GIFT_CRITERIA;
import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.DecemberEvent;
import christmas.domain.Menu;

public class PriceDiscount {
    private final Menu menu;

    public PriceDiscount(Menu menu) {
        this.menu = menu;
    }

    public int calculateGiftBenefit() {
        if (menu.sumPrice() >= GIFT_CRITERIA) {
            return DecemberEvent.GIFT_EVENTS.getDiscount();
        }
        return ZERO_DISCOUNT;
    }
}
