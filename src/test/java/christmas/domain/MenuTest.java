package christmas.domain;

import christmas.constants.MenuCategory;
import christmas.constants.MenuItem;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenuTest {
    @ParameterizedTest
    @DisplayName("정상 생성 테스트-StringToMenuItem")
    @MethodSource
    void createMenuTest(HashMap<String, Integer> actual, EnumMap<MenuItem, Integer> expect) {
        Menu menu = new Menu(actual);
        Assertions.assertThat(menu).extracting("menus").isEqualTo(expect);
    }

    private static Stream<Arguments> createMenuTest() {
        Map<String, Integer> actual = new HashMap<>();
        EnumMap<MenuItem, Integer> expect = new EnumMap<>(MenuItem.class);
        actual.put(MenuItem.T_BONE_STEAK.getName(), 1);
        actual.put(MenuItem.CHAMPAGNE.getName(), 1);
        actual.put(MenuItem.RED_WINE.getName(), 1);
        expect.put(MenuItem.T_BONE_STEAK, 1);
        expect.put(MenuItem.CHAMPAGNE, 1);
        expect.put(MenuItem.RED_WINE, 1);
        return Stream.of(
                Arguments.of(actual, expect)
        );
    }

    @ParameterizedTest
    @DisplayName("메뉴 전체 가격 계산 기능")
    @MethodSource
    void sumPriceTest(HashMap<String, Integer> given, int expect) {
        Menu menu = new Menu(given);
        Assertions.assertThat(menu.sumPrice()).isEqualTo(expect);
    }

    private static Stream<Arguments> sumPriceTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.T_BONE_STEAK.getName(), 1);
        actual.put(MenuItem.CHAMPAGNE.getName(), 2);
        actual.put(MenuItem.RED_WINE.getName(), 3);
        int sumPrise = (MenuItem.T_BONE_STEAK.getPrice());
        sumPrise += (MenuItem.CHAMPAGNE.getPrice() * 2);
        sumPrise += (MenuItem.RED_WINE.getPrice() * 3);

        return Stream.of(
                Arguments.of(actual, sumPrise)
        );
    }

    @ParameterizedTest
    @DisplayName("카테고리에 해당하는 메뉴 갯수 계산 기능")
    @MethodSource
    void sumMenuCountByMenuCategoryTest(HashMap<String, Integer> given, MenuCategory menuCategory, int expect) {
        Menu menu = new Menu(given);
        Assertions.assertThat(menu.sumMenuCountByMenuCategory(menuCategory)).isEqualTo(expect);
    }

    private static Stream<Arguments> sumMenuCountByMenuCategoryTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.T_BONE_STEAK.getName(), 1);
        actual.put(MenuItem.CHAMPAGNE.getName(), 2);
        actual.put(MenuItem.RED_WINE.getName(), 3);
        int expect = 2 + 3;

        return Stream.of(
                Arguments.of(actual, MenuCategory.BEVERAGE, expect)
        );
    }

}
