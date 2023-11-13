package christmas.service;

import christmas.constants.DecemberEvent;
import christmas.domain.Date;
import christmas.domain.TodayBenefits;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TodayBenefitServiceTest {

    @ParameterizedTest
    @DisplayName("타겟 요일에 해당하는 날짜인지 판별하는 기능 테스트")
    @MethodSource
    void isContainWeekTest(int day, List<DecemberEvent> expect) {
        Date date = new Date(day);
        TodayBenefitService todayBenefitService = new TodayBenefitService(date);
        TodayBenefits todayBenefits = todayBenefitService.getTodayEvents();
        Assertions.assertThat(todayBenefits).extracting("benefits").isEqualTo(expect);
    }

    private static Stream<Arguments> isContainWeekTest() {
        return Stream.of(
                Arguments.of(1, List.of(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, DecemberEvent.WEEKEND_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS)),
                Arguments.of(31, List.of(DecemberEvent.WEEKDAY_DISCOUNT, DecemberEvent.SPECIAL_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS)),
                Arguments.of(12, List.of(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, DecemberEvent.WEEKDAY_DISCOUNT,
                        DecemberEvent.GIFT_EVENTS)),
                Arguments.of(25, List.of(DecemberEvent.CHRISTMAS_D_DAY_DISCOUNT, DecemberEvent.WEEKDAY_DISCOUNT,
                        DecemberEvent.SPECIAL_DISCOUNT, DecemberEvent.GIFT_EVENTS))
        );
    }

}