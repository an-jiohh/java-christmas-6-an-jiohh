package christmas.service;

import static christmas.constants.Constants.MINIMUM_ORDER_PRICE;

import christmas.constants.DecemberEvent;
import christmas.discountpolicy.FullDiscount;
import christmas.discountpolicy.MenuDiscount;
import christmas.discountpolicy.PriceDiscount;
import christmas.domain.Date;
import christmas.domain.Discounts;
import christmas.domain.Menu;
import christmas.domain.TodayBenefits;
import java.util.HashMap;

public class DiscountService {
    private final Date date;
    private final Menu menu;
    private final TodayBenefits todayBenefits;


    public DiscountService(Date date, Menu menu, TodayBenefits todayBenefits) {
        this.date = date;
        this.menu = menu;
        this.todayBenefits = todayBenefits;
    }

    public Discounts calculateDiscount() {
        HashMap<DecemberEvent, Integer> discounts = new HashMap<>();
        if (isApplyDiscount()) {
            discounts.putAll(calculateFullPriceDiscount());
            discounts.putAll(calculateMenuDiscount());
            discounts.putAll(calculatePriceDiscount());
        }
        return new Discounts(discounts);
    }

    private HashMap<DecemberEvent, Integer> calculateFullPriceDiscount() {
        HashMap<DecemberEvent, Integer> discounts = new HashMap<>();
        FullDiscount fullPriceDiscount = new FullDiscount(date);
        if (todayBenefits.isContainEvents(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT)) {
            int christmasDDAYDiscount = fullPriceDiscount.calculateChristmasDDAYDiscount();
            discounts.put(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, christmasDDAYDiscount);
        }
        if (todayBenefits.isContainEvents(DecemberEvent.SPECIAL_DISCOUNT)) {
            int specialDiscount = fullPriceDiscount.calculateSpecialDiscount();
            discounts.put(DecemberEvent.SPECIAL_DISCOUNT, specialDiscount);
        }
        return discounts;
    }

    private HashMap<DecemberEvent, Integer> calculateMenuDiscount() {
        HashMap<DecemberEvent, Integer> discounts = new HashMap<>();
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        if (todayBenefits.isContainEvents(DecemberEvent.WEEKDAY_DISCOUNT)) {
            int weekDayDiscount = menuDiscount.calculateWeekDayDiscount();
            discounts.put(DecemberEvent.WEEKDAY_DISCOUNT, weekDayDiscount);
        }
        if (todayBenefits.isContainEvents(DecemberEvent.WEEKEND_DISCOUNT)) {
            int weekEndDiscount = menuDiscount.calculateWeekEndDiscount();
            discounts.put(DecemberEvent.WEEKEND_DISCOUNT, weekEndDiscount);
        }
        return discounts;
    }


    private HashMap<DecemberEvent, Integer> calculatePriceDiscount() {
        HashMap<DecemberEvent, Integer> discounts = new HashMap<>();
        PriceDiscount priceDiscount = new PriceDiscount(menu);
        if (todayBenefits.isContainEvents(DecemberEvent.GIFT_EVENTS)) {
            int giftBenefit = priceDiscount.calculateGiftBenefit();
            discounts.put(DecemberEvent.GIFT_EVENTS, giftBenefit);
        }
        return discounts;
    }

    private boolean isApplyDiscount() {
        return menu.sumPrice() >= MINIMUM_ORDER_PRICE;
    }
}
