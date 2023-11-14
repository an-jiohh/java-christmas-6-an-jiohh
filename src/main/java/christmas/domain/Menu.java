package christmas.domain;

import christmas.constants.MenuCategory;
import christmas.constants.MenuItem;
import christmas.validation.MenuValidator;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public int sumPrice() {
        return this.menus.entrySet().stream().mapToInt(entry -> calculateMenuPrice(entry)).sum();
    }

    private int calculateMenuPrice(Map.Entry<MenuItem, Integer> menuAndCount) {
        MenuItem menu = menuAndCount.getKey();
        int count = menuAndCount.getValue();
        return menu.getPrice() * count;
    }

    public int sumMenuCountByMenuCategory(MenuCategory menuCategory) {
        List<MenuItem> menusFromCategory = getMenusFromCategory(menuCategory);
        return menusFromCategory.stream().mapToInt(menus::get).sum();
    }

    private List<MenuItem> getMenusFromCategory(MenuCategory menuCategory) {
        return menus.keySet().stream().filter(key -> key.getCategory() == menuCategory).collect(Collectors.toList());
    }

    public Map<MenuItem, Integer> getMenus() {
        return Collections.unmodifiableMap(new EnumMap<>(menus));
    }
}
