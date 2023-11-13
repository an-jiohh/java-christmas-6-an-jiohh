package christmas.discountpolicy;

import christmas.constants.DecemberEvent;
import christmas.domain.Date;

public class FullDiscount {
    private final Date date;

    public FullDiscount(Date date) {
        this.date = date;
    }

    public int calculateChristmasDDAYDiscount() {
        int discount = DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT.getDiscount();
        discount += date.getDate() * DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT.getIncrementDiscount();
        return discount;
    }

    public int calculateSpecialDiscount() {
        return DecemberEvent.SPECIAL_DISCOUNT.getDiscount();
    }
}
