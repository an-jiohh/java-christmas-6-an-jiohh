package christmas.discountpolicy;

import christmas.domain.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FullDiscountTest {
    @ParameterizedTest
    @DisplayName("크리스마스 할인 금액 계산")
    @CsvSource(value = {"1:1000", "2:1100", "25:3400"}, delimiter = ':')
    void calculateChristmasDDAYDiscountTest(int number, int expect) {
        Date actual = new Date(number);
        FullDiscount fullDiscount = new FullDiscount(actual);
        Assertions.assertThat(fullDiscount.calculateChristmasDDAYDiscount()).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("특별 할인 금액 계산")
    @CsvSource(value = {"3:1000", "10:1000", "17:1000", "24:1000", "31:1000", "25:1000"}, delimiter = ':')
    void calculateSpecialDiscountTest(int number, int expect) {
        Date actual = new Date(number);
        FullDiscount fullDiscount = new FullDiscount(actual);
        Assertions.assertThat(fullDiscount.calculateSpecialDiscount()).isEqualTo(expect);
    }
}