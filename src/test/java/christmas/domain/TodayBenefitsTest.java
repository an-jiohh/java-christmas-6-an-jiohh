package christmas.domain;

import christmas.constants.DecemberEvent;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TodayBenefitsTest {

    @ParameterizedTest
    @DisplayName("혜택 포함 체크 기능 테스트")
    @MethodSource
    void isContainEventsTest(List<DecemberEvent> given, DecemberEvent target, boolean expect) {
        TodayBenefits todayBenefits = new TodayBenefits(given);
        Assertions.assertThat(todayBenefits.isContainEvents(target)).isEqualTo(expect);
    }

    private static Stream<Arguments> isContainEventsTest() {
        return Stream.of(
                Arguments.of(List.of(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, DecemberEvent.WEEKEND_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS), DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, true),
                Arguments.of(List.of(DecemberEvent.WEEKDAY_DISCOUNT, DecemberEvent.SPECIAL_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS), DecemberEvent.WEEKEND_DISCOUNT, false),
                Arguments.of(List.of(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, DecemberEvent.WEEKDAY_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS), DecemberEvent.GIFT_EVENTS, true),
                Arguments.of(List.of(DecemberEvent.WEEKDAY_DISCOUNT,
                                DecemberEvent.SPECIAL_DISCOUNT, DecemberEvent.GIFT_EVENTS),
                        DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, false)
        );
    }


}