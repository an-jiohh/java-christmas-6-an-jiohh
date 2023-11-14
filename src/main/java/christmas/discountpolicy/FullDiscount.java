package christmas.discountpolicy;

import christmas.constants.Event;
import christmas.domain.Date;

public class FullDiscount {
    private final Date date;

    public FullDiscount(Date date) {
        this.date = date;
    }

    public int calculateChristmasDDAYDiscount() {
        int discount = Event.CHRISTMAS_D_DAY_DISCOUNT.getDiscount();
        discount += date.getDate() * Event.CHRISTMAS_D_DAY_DISCOUNT.getIncrementDiscount();
        return discount;
    }

    public int calculateSpecialDiscount() {
        return Event.SPECIAL_DISCOUNT.getDiscount();
    }
}
