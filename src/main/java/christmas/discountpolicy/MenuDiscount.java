package christmas.discountpolicy;

import christmas.constants.DecemberEvent;
import christmas.constants.MenuCategory;
import christmas.domain.Menu;

public class MenuDiscount {

    private final Menu menu;

    public MenuDiscount(Menu menu) {
        this.menu = menu;
    }

    public int calculateWeekDayDiscount() {
        int dessertCount = menu.sumMenuCountByMenuCategory(MenuCategory.DESSERT);
        return dessertCount * DecemberEvent.WEEKDAY_DISCOUNT.getDiscount();
    }

    public int calculateWeekEndDiscount() {
        int mainCount = menu.sumMenuCountByMenuCategory(MenuCategory.MAIN);
        return mainCount * DecemberEvent.WEEKEND_DISCOUNT.getDiscount();
    }

}
