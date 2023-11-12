package christmas.validation;

import christmas.exception.IncludeCharException;
import christmas.exception.InputBlankException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void initTest() {
        inputValidator = new InputValidator();
    }

    @ParameterizedTest
    @DisplayName("숫자가 입력되었을떄 정상처리")
    @CsvSource({"'1'", "'2'", "'3'"})
    void validateNumberTest(String readLine) {
        Assertions.assertThatCode(() -> inputValidator.validateNumber(readLine))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 문자가 입력되었을때")
    @CsvSource({"'ㄱ'", "'가나'", "'1,2'"})
    void checkDigitTest(String readLine) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateNumber(readLine))
                .isInstanceOf(IncludeCharException.class);
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 빈칸이 입력되었을때")
    @CsvSource({"''", "'  '", "'          '"})
    void checkBlackTest(String readLine) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateNumber(readLine))
                .isInstanceOf(InputBlankException.class);
    }

}
