package christmas.discountpolicy;

import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.Event;
import christmas.constants.MenuItem;
import christmas.domain.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuDiscountTest {

    @ParameterizedTest
    @DisplayName("평일할인 계산 테스트")
    @MethodSource
    void calculateWeekDayDiscountTest(HashMap<String, Integer> actual, int expect) {
        Menu menu = new Menu(actual);
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        Assertions.assertThat(menuDiscount.calculateWeekDayDiscount()).isEqualTo(expect);
    }

    private static Stream<Arguments> calculateWeekDayDiscountTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.T_BONE_STEAK.getName(), 2);
        actual.put(MenuItem.BBQ_RIBS.getName(), 1);
        actual.put(MenuItem.CHOCOLATE_CAKE.getName(), 1);
        actual.put(MenuItem.ICE_CREAM.getName(), 1);
        return Stream.of(
                Arguments.of(actual, 2 * Event.WEEKDAY_DISCOUNT.getDiscount())
        );
    }

    @ParameterizedTest
    @DisplayName("평일할인 메뉴없음 계산 테스트")
    @MethodSource
    void noneWeekDayDiscountTest(HashMap<String, Integer> actual, int expect) {
        Menu menu = new Menu(actual);
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        Assertions.assertThat(menuDiscount.calculateWeekDayDiscount()).isEqualTo(expect);
    }

    private static Stream<Arguments> noneWeekDayDiscountTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.T_BONE_STEAK.getName(), 2);
        actual.put(MenuItem.BBQ_RIBS.getName(), 1);
        return Stream.of(
                Arguments.of(actual, ZERO_DISCOUNT)
        );
    }

    @ParameterizedTest
    @DisplayName("주말할인 계산 테스트")
    @MethodSource
    void calculateEndDayDiscountTest(HashMap<String, Integer> actual, int expect) {
        Menu menu = new Menu(actual);
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        Assertions.assertThat(menuDiscount.calculateWeekEndDiscount()).isEqualTo(expect);
    }

    private static Stream<Arguments> calculateEndDayDiscountTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.T_BONE_STEAK.getName(), 2);
        actual.put(MenuItem.BBQ_RIBS.getName(), 1);
        actual.put(MenuItem.CHOCOLATE_CAKE.getName(), 1);
        actual.put(MenuItem.ICE_CREAM.getName(), 1);
        return Stream.of(
                Arguments.of(actual, 3 * Event.WEEKEND_DISCOUNT.getDiscount())
        );
    }

    @ParameterizedTest
    @DisplayName("주말할인 메뉴없음 계산 테스트")
    @MethodSource
    void noneEndDayDiscountTest(HashMap<String, Integer> actual, int expect) {
        Menu menu = new Menu(actual);
        MenuDiscount menuDiscount = new MenuDiscount(menu);
        Assertions.assertThat(menuDiscount.calculateWeekEndDiscount()).isEqualTo(expect);
    }

    private static Stream<Arguments> noneEndDayDiscountTest() {
        Map<String, Integer> actual = new HashMap<>();
        actual.put(MenuItem.CHOCOLATE_CAKE.getName(), 1);
        actual.put(MenuItem.ICE_CREAM.getName(), 1);
        return Stream.of(
                Arguments.of(actual, ZERO_DISCOUNT)
        );
    }
}