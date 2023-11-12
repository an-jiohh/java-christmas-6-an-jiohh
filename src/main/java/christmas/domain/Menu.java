package christmas.domain;

import christmas.constants.MenuItem;
import christmas.validation.MenuValidator;
import java.util.EnumMap;
import java.util.Map;

public class Menu {
    private final EnumMap<MenuItem, Integer> menus;

    public Menu(Map<String, Integer> noneConstantsMenus) {
        MenuValidator.validateMenu(noneConstantsMenus);
        EnumMap<MenuItem, Integer> menus = menuConstantsConverter(noneConstantsMenus);
        MenuValidator.checkOrderOnlyBeverage(menus);
        this.menus = menus;
    }

    private EnumMap<MenuItem, Integer> menuConstantsConverter(Map<String, Integer> noneConstantsMenus) {
        EnumMap<MenuItem, Integer> creteMenu = new EnumMap<>(MenuItem.class);
        noneConstantsMenus.forEach((key, value) -> {
            MenuItem menuKey = MenuItem.fromName(key);
            creteMenu.put(menuKey, value);
        });
        return creteMenu;
    }

}
