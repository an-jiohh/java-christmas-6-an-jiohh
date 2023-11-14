package christmas.view;

import static christmas.constants.OutputMessage.ALL_BENEFITS_AMOUNT_HEAD;
import static christmas.constants.OutputMessage.ALL_DISCOUNT_HEAD;
import static christmas.constants.OutputMessage.ALL_PRICE_HEAD;
import static christmas.constants.OutputMessage.BLANK;
import static christmas.constants.OutputMessage.COUNT_UNIT;
import static christmas.constants.OutputMessage.DISCOUNT_AMOUNT_SEPARATOR;
import static christmas.constants.OutputMessage.EVENT_BADGE_HEAD;
import static christmas.constants.OutputMessage.GIFT_MENU_HEAD;
import static christmas.constants.OutputMessage.MENU_HEAD;
import static christmas.constants.OutputMessage.NEGATIVE_MARK;
import static christmas.constants.OutputMessage.NO_DISCOUNT;
import static christmas.constants.OutputMessage.NUMBER_FORMAT;
import static christmas.constants.OutputMessage.ONE_CHAMPAGNE;
import static christmas.constants.OutputMessage.PAYMENT_AMOUNT_HEAD;
import static christmas.constants.OutputMessage.PRICE_UNIT;

import christmas.constants.DecemberEvent;
import christmas.constants.MenuItem;
import christmas.domain.Discounts;
import christmas.domain.Menu;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private final DecimalFormat formatter = new DecimalFormat(NUMBER_FORMAT);

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printAllMenu(Menu menu) {
        Map<MenuItem, Integer> menus = menu.getMenus();
        System.out.println(MENU_HEAD);
        menus.forEach((menuItem, count) -> {
            System.out.println(menuItem.getName() + BLANK + count + COUNT_UNIT);
        });
    }

    public void printBeforeDiscountPrice(Menu menu) {
        int allPrice = menu.sumPrice();
        System.out.println(ALL_PRICE_HEAD);
        System.out.println(formatter.format(allPrice) + PRICE_UNIT);
    }

    public void printGiftMenu(Discounts discounts) {
        System.out.println(GIFT_MENU_HEAD);
        if (discounts.isContainEvent(DecemberEvent.GIFT_EVENTS)) {
            System.out.println(ONE_CHAMPAGNE);
            return;
        }
        System.out.println(NO_DISCOUNT);
    }

    public void printAllDiscount(Discounts discounts) {
        System.out.println(ALL_DISCOUNT_HEAD);
        if (discounts.isDiscountEmpty()) {
            System.out.println(NO_DISCOUNT);
            return;
        }
        Map<DecemberEvent, Integer> discount = discounts.getDiscounts();
        discount.forEach(((event, benefit) -> System.out.println(
                event.getName() + DISCOUNT_AMOUNT_SEPARATOR + formatter.format(benefit) + PRICE_UNIT)));
    }

    public void printAllBenefitsAmount(Discounts discounts) {
        int allAmount = discounts.sumAllBenefitsAmount();
        System.out.println(ALL_BENEFITS_AMOUNT_HEAD);
        System.out.println(NEGATIVE_MARK + formatter.format(allAmount) + PRICE_UNIT);
    }

    public void printPaymentAmount(Menu menu, Discounts discounts) {
        System.out.println(PAYMENT_AMOUNT_HEAD);
        int totalPrice = menu.sumPrice() - discounts.sumAllDiscountAmount();
        System.out.println(formatter.format(totalPrice) + PRICE_UNIT);
    }

    public void printEventBadge(Discounts discounts) {
        System.out.println(EVENT_BADGE_HEAD);
        System.out.println(discounts.createEventBadge().getName());
    }
}
