package christmas.domain;

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
    @DisplayName("정상 생성 테스트")
    @MethodSource
    void checkOrderOnlyBeverageTest(HashMap<String, Integer> actual, EnumMap<MenuItem, Integer> expect) {
        Menu menu = new Menu(actual);
        Assertions.assertThat(menu).extracting("menus").isEqualTo(expect);
    }

    private static Stream<Arguments> checkOrderOnlyBeverageTest() {
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

}
