package christmas.validation;

import static christmas.constants.Constants.MAX_MENU_COUNT;
import static christmas.constants.Constants.ZERO_COUNT;

import christmas.constants.MenuCategory;
import christmas.constants.MenuItem;
import christmas.exception.BeverageOnlyOrderException;
import christmas.exception.CountRangeException;
import christmas.exception.MenuLimitExceededException;
import christmas.exception.MenuNotFoundException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MenuValidator {
    public static void validateMenu(Map<String, Integer> noneConstantsMenus) {
        checkExistenceMenu(noneConstantsMenus);
        checkCountOverflow(noneConstantsMenus);
        checkCountRange(noneConstantsMenus);
    }

    public static void checkOrderOnlyBeverage(EnumMap<MenuItem, Integer> menus) {
        if (isBeverageOnlyOrder(menus)) {
            throw BeverageOnlyOrderException.exception;
        }
    }

    private static boolean isBeverageOnlyOrder(EnumMap<MenuItem, Integer> menus) {
        return menus.keySet().stream().allMatch(item -> item.getCategory() == MenuCategory.BEVERAGE);
    }

    private static void checkExistenceMenu(Map<String, Integer> noneConstantsMenus) {
        List<String> menuNames = MenuItem.getNames();
        boolean isNoneExistence = noneConstantsMenus.keySet().stream()
                .anyMatch(menuName -> !menuNames.contains(menuName));
        if (isNoneExistence) {
            throw MenuNotFoundException.exception;
        }
    }

    private static void checkCountOverflow(Map<String, Integer> noneConstantsMenus) {
        int CountSum = noneConstantsMenus.values().stream().mapToInt(Integer::intValue).sum();
        if (MAX_MENU_COUNT < CountSum) {
            throw MenuLimitExceededException.exception;
        }
    }

    private static void checkCountRange(Map<String, Integer> noneConstantsMenus) {
        boolean isCountRange = noneConstantsMenus.values().stream().anyMatch(value -> value <= ZERO_COUNT);
        if (isCountRange) {
            throw CountRangeException.exception;
        }
    }

}
