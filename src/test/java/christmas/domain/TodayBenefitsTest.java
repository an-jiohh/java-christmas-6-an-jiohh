package christmas.domain;

import christmas.constants.Event;
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
    void isContainEventsTest(List<Event> given, Event target, boolean expect) {
        TodayBenefits todayBenefits = new TodayBenefits(given);
        Assertions.assertThat(todayBenefits.isContainEvents(target)).isEqualTo(expect);
    }

    private static Stream<Arguments> isContainEventsTest() {
        return Stream.of(
                Arguments.of(List.of(Event.CHRISTMAS_D_DAY_DISCOUNT, Event.WEEKEND_DISCOUNT,
                        Event.GIFT_EVENTS), Event.CHRISTMAS_D_DAY_DISCOUNT, true),
                Arguments.of(List.of(Event.WEEKDAY_DISCOUNT, Event.SPECIAL_DISCOUNT,
                        Event.GIFT_EVENTS), Event.WEEKEND_DISCOUNT, false),
                Arguments.of(List.of(Event.CHRISTMAS_D_DAY_DISCOUNT, Event.WEEKDAY_DISCOUNT,
                        Event.GIFT_EVENTS), Event.GIFT_EVENTS, true),
                Arguments.of(List.of(Event.WEEKDAY_DISCOUNT,
                                Event.SPECIAL_DISCOUNT, Event.GIFT_EVENTS),
                        Event.CHRISTMAS_D_DAY_DISCOUNT, false)
        );
    }


}