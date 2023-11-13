package christmas.domain;

import christmas.constants.Week;
import christmas.exception.DateRangeException;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class DateTest {
    @ParameterizedTest
    @DisplayName("정상으로 생성되는지 확인")
    @CsvSource({"1", "2", "31"})
    void dateCreateTest(int number) {
        Date actual = new Date(number);
        Assertions.assertThat(actual).extracting("date").isEqualTo(number);
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 12월 범위에 넘어갔을때")
    @CsvSource({"0", "-1", "32"})
    void checkDateRangeTest(int number) {
        Assertions.assertThatThrownBy(() -> new Date(number))
                .isInstanceOf(DateRangeException.class);
    }

    @ParameterizedTest
    @DisplayName("날짜로 요일을 알아내는 기능 테스트")
    @MethodSource
    void getWeekTest(int day, Week expect) {
        Date date = new Date(day);
        Assertions.assertThat(date.getWeek()).isEqualTo(expect);
    }

    private static Stream<Arguments> getWeekTest() {
        return Stream.of(
                Arguments.of(1, Week.FRIDAY),
                Arguments.of(5, Week.TUESDAY),
                Arguments.of(14, Week.THURSDAY),
                Arguments.of(25, Week.MONDAY),
                Arguments.of(31, Week.SUNDAY)
        );
    }

    @ParameterizedTest
    @DisplayName("타겟 요일에 해당하는 날짜인지 판별하는 기능 테스트")
    @MethodSource
    void isContainWeekTest(int day, List<Week> targetWeek, boolean expect) {
        Date date = new Date(day);
        Assertions.assertThat(date.isContainWeeks(targetWeek)).isEqualTo(expect);
    }

    private static Stream<Arguments> isContainWeekTest() {
        return Stream.of(
                Arguments.of(1, List.of(Week.FRIDAY, Week.SATURDAY), true),
                Arguments.of(1, List.of(Week.FRIDAY, Week.SATURDAY), true),
                Arguments.of(25, List.of(Week.SUNDAY, Week.MONDAY), true),
                Arguments.of(1, List.of(Week.SUNDAY, Week.MONDAY), false),
                Arguments.of(1, List.of(), false)
        );
    }

    @ParameterizedTest
    @DisplayName("타겟 날에 해당하는 날짜인지 판별하는 기능 테스트")
    @MethodSource
    void isContainDaysTest(int day, List<Integer> targetDays, boolean expect) {
        Date date = new Date(day);
        Assertions.assertThat(date.isContainDays(targetDays)).isEqualTo(expect);
    }

    private static Stream<Arguments> isContainDaysTest() {
        return Stream.of(
                Arguments.of(1, List.of(1, 2, 3, 4, 5, 6), true),
                Arguments.of(23, List.of(2, 3, 23, 24), true),
                Arguments.of(25, List.of(26, 27, 18), false),
                Arguments.of(31, List.of(29, 30, 31), true),
                Arguments.of(31, List.of(), false)
        );
    }
}
