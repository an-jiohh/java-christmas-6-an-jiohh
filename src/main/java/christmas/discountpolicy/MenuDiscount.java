package christmas.discountpolicy;

import christmas.constants.Event;
import christmas.constants.MenuCategory;
import christmas.domain.Menu;

public class MenuDiscount {

    private final Menu menu;

    public MenuDiscount(Menu menu) {
        this.menu = menu;
    }

    public int calculateWeekDayDiscount() {
        int dessertCount = menu.sumMenuCountByMenuCategory(MenuCategory.DESSERT);
        return dessertCount * Event.WEEKDAY_DISCOUNT.getDiscount();
    }

    public int calculateWeekEndDiscount() {
        int mainCount = menu.sumMenuCountByMenuCategory(MenuCategory.MAIN);
        return mainCount * Event.WEEKEND_DISCOUNT.getDiscount();
    }

}
