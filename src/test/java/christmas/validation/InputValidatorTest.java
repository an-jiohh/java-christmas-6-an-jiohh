package christmas.validation;

import christmas.exception.CountIncludeCharException;
import christmas.exception.InputBlankException;
import christmas.exception.InvalidMenuCountSeparatorException;
import christmas.exception.InvalidMenusSeparatorException;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void initTest() {
        inputValidator = new InputValidator();
    }

    @ParameterizedTest
    @DisplayName("날짜가 입력되었을떄 정상처리")
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
                .isInstanceOf(CountIncludeCharException.class);
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 빈칸이 입력되었을때")
    @CsvSource({"''", "'  '", "'          '"})
    void checkBlackTest(String readLine) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateNumber(readLine))
                .isInstanceOf(InputBlankException.class);
    }

    @ParameterizedTest
    @DisplayName("메뉴가 입력되었을떄 정상처리")
    @CsvSource({"'피자-1,통닭-2,짜장면-3'"})
    void validateMenuDataTest(String readLine) {
        Assertions.assertThatCode(() -> inputValidator.validateMenuLine(readLine))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 메뉴간,구분을 잘못하였을때")
    @CsvSource({"'피자-1,통닭-2,,짜장면-3'", "',피자-1,통닭-2,짜장면-3'", "'피자-1,통닭-2,,짜장면-3,'"})
    void validateMenuLineTest(String readLine) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateMenuLine(readLine))
                .isInstanceOf(InvalidMenusSeparatorException.class);
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 메뉴와개수간 -구분을 잘못 사용하였을때")
    @MethodSource
    void validateMenuCountSplitTest(List<String> menus) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateMenuCountSplit(menus))
                .isInstanceOf(InvalidMenuCountSeparatorException.class);
    }

    private static Stream<Arguments> validateMenuCountSplitTest() {
        return Stream.of(
                Arguments.of(List.of("-피자-1", "통닭-2", "짜장면-3")),
                Arguments.of(List.of("피자-1", "통닭--2", "짜장면-3")),
                Arguments.of(List.of("피자-1", "통닭-2", "짜장면-3-"))
        );
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 분리된 메뉴이름이 잘못되었을때")
    @MethodSource
    void validateMenuTest(List<String[]> menus) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateMenu(menus))
                .isInstanceOf(InputBlankException.class);
    }

    private static Stream<Arguments> validateMenuTest() {
        return Stream.of(
                Arguments.of(List.of(new String[]{"피자", "1"}, new String[]{"  ", "2"}, new String[]{"짜장면", "3"})),
                Arguments.of(List.of(new String[]{"", "1"}, new String[]{"통닭", "2"}, new String[]{"짜장면", "3"}))
        );
    }

    @ParameterizedTest
    @DisplayName("[ERROR] 분리된 메뉴개수가 잘못되었을때")
    @MethodSource
    void validateCountTest(List<String[]> menus, Exception exception) {
        Assertions.assertThatThrownBy(() -> inputValidator.validateCount(menus))
                .isInstanceOf(exception.getClass());
    }

    private static Stream<Arguments> validateCountTest() {
        return Stream.of(
                Arguments.of(List.of(new String[]{"피자", "하나"}, new String[]{"통닭", "2"}, new String[]{"짜장면", "3"}),
                        CountIncludeCharException.exception),
                Arguments.of(List.of(new String[]{"피자", "1"}, new String[]{"통닭", ""}, new String[]{"짜장면", "3"}),
                        InputBlankException.exception),
                Arguments.of(List.of(new String[]{"피자", "1"}, new String[]{"통닭", ""}, new String[]{"짜장면", "  "}),
                        InputBlankException.exception)
        );
    }
}
