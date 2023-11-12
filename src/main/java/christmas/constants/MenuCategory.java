package christmas.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuCategory {
    APPETIZER,
    MAIN,
    DESSERT,
    BEVERAGE;

    public List<MenuItem> getMenus() {
        return Arrays.stream(MenuItem.values())
                .filter(menu -> menu.getCategory() == this)
                .collect(Collectors.toList());
    }
}
