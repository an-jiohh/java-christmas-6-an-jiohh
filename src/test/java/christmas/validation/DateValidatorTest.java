package christmas.validation;

import christmas.exception.DateRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DateValidatorTest {

    @ParameterizedTest
    @DisplayName("12월에 해당하는 날짜를 입력했을때 정상처리")
    @CsvSource({"1", "2", "31"})
    void validateDateTest(int number) {
        Assertions.assertThatCode(() -> DateValidator.validateDate(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 12월 범위에 넘어갔을때")
    @CsvSource({"0", "-1", "32"})
    void checkDateRangeTest(int number) {
        Assertions.assertThatThrownBy(() -> DateValidator.validateDate(number))
                .isInstanceOf(DateRangeException.class);
    }
}
