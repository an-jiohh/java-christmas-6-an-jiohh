package christmas.domain;

import christmas.exception.DateRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
