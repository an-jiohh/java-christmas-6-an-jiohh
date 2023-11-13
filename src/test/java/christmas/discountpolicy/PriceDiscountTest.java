package christmas.discountpolicy;

import static christmas.constants.Constants.ZERO_DISCOUNT;

import christmas.constants.DecemberEvent;
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

class PriceDiscountTest {
    @ParameterizedTest
    @DisplayName("증정이벤트 계산 테스트")
    @MethodSource
    void calculateWeekDayDiscountTest(HashMap<String, Integer> actual, int expect) {
        Menu menu = new Menu(actual);
        PriceDiscount priceDiscount = new PriceDiscount(menu);
        Assertions.assertThat(priceDiscount.calculateGiftBenefit()).isEqualTo(expect);
    }

    private static Stream<Arguments> calculateWeekDayDiscountTest() {
        Map<String, Integer> gift = new HashMap<>();
        gift.put(MenuItem.T_BONE_STEAK.getName(), 10);
        gift.put(MenuItem.BBQ_RIBS.getName(), 1);
        Map<String, Integer> noneGift = new HashMap<>();
        noneGift.put(MenuItem.BBQ_RIBS.getName(), 1);
        return Stream.of(
                Arguments.of(gift, DecemberEvent.GIFT_EVENTS.getDiscount()),
                Arguments.of(noneGift, ZERO_DISCOUNT)
        );
    }

}