package christmas.service;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuServiceTest extends NsTest {

    @ParameterizedTest
    @DisplayName("[예외] 빈칸 입력 테스트")
    @CsvSource({"' '", "'  '"})
    void blankMenuTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] ,을 잘못 사용했을때")
    @CsvSource({"',,'", "','", "'해산물파스타-2,,레드와인-1,초코케이크-1'", "'해산물파스타-2,레드와인-1,초코케이크-1,'",
            "',해산물파스타-2,레드와인-1,초코케이크-1'"})
    void menuSeparateTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 메뉴,갯수 구분(-)을 잘못 사용했을때")
    @CsvSource({"'해산물파스타2'", "'해산물파스타--2'", "'해산물파스타-2-'", "'-해산물파스타-2'"})
    void menuCountSeparateTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 메뉴가 빈값일때")
    @CsvSource({"'-2'", "'  -2'", "'      -2'"})
    void menuBlankTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 개수가 빈값일때")
    @CsvSource({"'해산물파스타-     '", "'해산물파스타-'"})
    void countBlankTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 개수가 숫자가 아닐때")
    @CsvSource({"'해산물파스타-한개'", "'해산물파스타-1개'"})
    void countCharTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 중복된 메뉴가 존재할때")
    @CsvSource({"'해산물파스타-1,해산물파스타-1'", "'해산물파스타-1,해산물파스타-1,초코케이크-1"})
    void duplicateMenuTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 존재하는 메뉴가 아닐때")
    @CsvSource({"'그냥파스타-1'", "'제로팹시-1"})
    void notFoundMenuTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 매뉴 개수가 20개를 넘을때")
    @CsvSource({"'해산물파스타-15,레드와인-6'", "'해산물파스타-10,바비큐립-10,초코케이크-10"})
    void menuSumRangeTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("[예외] 음료만 주문했을때")
    @CsvSource({"'레드와인-1'", "'제로콜라-1,레드와인-1,샴페인-1'"})
    void menuOnlyBeverageTest(String readLine) {
        assertSimpleTest(() -> {
            runException(readLine);
            assertThat(output()).contains(MENU_EXCEPTION_MESSAGE);
        });
    }


    @Override
    protected void runMain() {
        MenuService menuService = new MenuService();
        Menu menu = menuService.fetchValidatedMenu();
    }
}