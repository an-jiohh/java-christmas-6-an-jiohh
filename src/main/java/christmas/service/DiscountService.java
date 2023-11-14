package christmas.service;

import static christmas.constants.Constants.MINIMUM_ORDER_PRICE;

import christmas.constants.Event;
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
        HashMap<Event, Integer> discounts = new HashMap<>();
        if (isApplyDiscount()) {
            discounts.putAll(calculateFullPriceDiscount());
            discounts.putAll(calculateMenuDiscount());
            discounts.putAll(calculatePriceDiscount());
        }
        return new Discounts(discounts);
    }

    private HashMap<Event, Integer> calculateFullPriceDiscount() {
        HashMap<Event, Integer> discounts = new HashMap<>();
        FullDiscount fullPriceDiscount = new FullDiscount(date);
        if (todayBenefits.isContainEvents(Event.CHRISTMAS_D_DAY_DISCOUNT)) {
            int christmasDDAYDiscount = fullPriceDiscount.calculateChristmasDDAYDiscount();
            discounts.put(Event.CHRISTMAS_D_DAY_DISCOUNT, christmasDDAYDiscount);
        }
        if (todayBenefits.isContainEvents(Event.SPECIAL_DISCOUNT)) {
            int specialDiscount = fullPriceDiscount.calculateSpecialDiscount();
            discounts.put(Event.SPECIAL_DISCOUNT, specialDiscount);
        }
        return discounts;
    }

    private HashMap<Event, Integer> calculateMenuDiscount() {
        HashMap<Event, Integer> discounts = new HashMap<>();
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        if (todayBenefits.isContainEvents(Event.WEEKDAY_DISCOUNT)) {
            int weekDayDiscount = menuDiscount.calculateWeekDayDiscount();
            discounts.put(Event.WEEKDAY_DISCOUNT, weekDayDiscount);
        }
        if (todayBenefits.isContainEvents(Event.WEEKEND_DISCOUNT)) {
            int weekEndDiscount = menuDiscount.calculateWeekEndDiscount();
            discounts.put(Event.WEEKEND_DISCOUNT, weekEndDiscount);
        }
        return discounts;
    }


    private HashMap<Event, Integer> calculatePriceDiscount() {
        HashMap<Event, Integer> discounts = new HashMap<>();
        PriceDiscount priceDiscount = new PriceDiscount(menu);
        if (todayBenefits.isContainEvents(Event.GIFT_EVENTS)) {
            int giftBenefit = priceDiscount.calculateGiftBenefit();
            discounts.put(Event.GIFT_EVENTS, giftBenefit);
        }
        return discounts;
    }

    private boolean isApplyDiscount() {
        return menu.sumPrice() >= MINIMUM_ORDER_PRICE;
    }
}
