package christmas.service;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.constants.ExceptionMessage.DATE_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateServiceTest extends NsTest {
    private Date date;

    @ParameterizedTest
    @DisplayName("정상적인 날짜입력 테스트")
    @CsvSource(value = {"'1':1", "'3':3", "'4':4", "'5':5", "'31':31"}, delimiter = ':')
    void normalDateServiceTest(String readLine, int expect) {
        assertSimpleTest(() -> {
            run(readLine);
            assertThat(date).extracting("date").isEqualTo(expect);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 빈칸 입력 테스트")
    @CsvSource({"' '", "'  '"})
    void blankDateTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(DATE_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 문자 입력 테스트")
    @CsvSource({"'하나'", "'크리스마스'"})
    void charDateTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(DATE_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 범위 테스트")
    @CsvSource({"'-1'", "'0'", "'32'", "'42'"})
    void dateRangeTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(DATE_EXCEPTION_MESSAGE);
        });
    }

    @Override
    protected void runMain() {
        DateService dateService = new DateService();
        date = dateService.fetchValidatedDate();
    }
}