package christmas.validation;

import christmas.constants.MenuItem;
import christmas.exception.BeverageOnlyOrderException;
import christmas.exception.MenuLimitExceededException;
import christmas.exception.MenuNotFoundException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenuValidatorTest {
    @ParameterizedTest
    @DisplayName("메뉴 생성 정상")
    @MethodSource
    void createMenuTest(Map<String, Integer> menus) {
        Assertions.assertThatCode(() -> MenuValidator.validateMenu(menus))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> createMenuTest() {
        Map<String, Integer> testMenus = new HashMap<>();
        testMenus.put("시저샐러드", 1);
        testMenus.put("초코케이크", 1);
        return Stream.of(
                Arguments.of(testMenus)
        );
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 존재하지 않는 메뉴일때")
    @MethodSource
    void checkExistenceMenuTest(Map<String, Integer> menus) {
        Assertions.assertThatThrownBy(() -> MenuValidator.validateMenu(menus))
                .isInstanceOf(MenuNotFoundException.class);
    }

    private static Stream<Arguments> checkExistenceMenuTest() {
        Map<String, Integer> testMenus = new HashMap<>();
        testMenus.put(MenuItem.SEAFOOD_PASTA.getName(), 1);
        testMenus.put("딸기케이크", 1);
        return Stream.of(
                Arguments.of(testMenus)
        );
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 메뉴 개수가 20개를 넘어갈때")
    @MethodSource
    void checkCountOverflowTest(Map<String, Integer> menus) {
        Assertions.assertThatThrownBy(() -> MenuValidator.validateMenu(menus))
                .isInstanceOf(MenuLimitExceededException.class);
    }

    private static Stream<Arguments> checkCountOverflowTest() {
        Map<String, Integer> testMenus = new HashMap<>();
        testMenus.put(MenuItem.CAESAR_SALAD.getName(), 1);
        testMenus.put(MenuItem.SEAFOOD_PASTA.getName(), 20);
        return Stream.of(
                Arguments.of(testMenus)
        );
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 음료만 주문할때")
    @MethodSource
    void checkOrderOnlyBeverageTest(EnumMap<MenuItem, Integer> menus) {
        Assertions.assertThatThrownBy(() -> MenuValidator.checkOrderOnlyBeverage(menus))
                .isInstanceOf(BeverageOnlyOrderException.class);
    }

    private static Stream<Arguments> checkOrderOnlyBeverageTest() {
        EnumMap<MenuItem, Integer> testMenus = new EnumMap<>(MenuItem.class);
        testMenus.put(MenuItem.ZERO_COLA, 1);
        testMenus.put(MenuItem.CHAMPAGNE, 1);
        testMenus.put(MenuItem.RED_WINE, 1);
        return Stream.of(
                Arguments.of(testMenus)
        );
    }


}
