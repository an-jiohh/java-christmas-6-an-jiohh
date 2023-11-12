package christmas.view;

import static christmas.constants.Constants.COUNT_POSITION;
import static christmas.constants.Constants.MENUS_SEPARATOR;
import static christmas.constants.Constants.MENU_COUNT_SEPARATOR;
import static christmas.constants.Constants.MENU_POSITION;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.DataTypeRangeException;
import christmas.exception.DuplicateMenuException;
import christmas.validation.InputValidator;
import java.util.HashMap;
import java.util.List;

public class InputView {

    private final InputValidator inputValidator;

    public InputView() {
        this.inputValidator = new InputValidator();
    }

    public int readNumberData() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String readLine = Console.readLine();
        inputValidator.validateNumber(readLine);
        return validatedNumberParser(readLine);
    }

    public HashMap<String, Integer> readMenuData() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String readLine = Console.readLine();
        List<String> menuAndCounts = splitMenuReadLine(readLine);
        List<String[]> separatedMenus = splitMenuAndCount(menuAndCounts);
        return menuAndCountConverter(separatedMenus);
    }

    private List<String> splitMenuReadLine(String readLine) {
        inputValidator.validateMenuLine(readLine);
        return List.of(readLine.split(MENUS_SEPARATOR));
    }

    private List<String[]> splitMenuAndCount(List<String> menuAndCounts) {
        inputValidator.validateMenuCountSplit(menuAndCounts);
        return menuAndCounts.stream().map(menuAndCount -> menuAndCount.split(MENU_COUNT_SEPARATOR)).toList();
    }

    private HashMap<String, Integer> menuAndCountConverter(List<String[]> separatedMenus) {
        inputValidator.validateMenu(separatedMenus);
        inputValidator.validateCount(separatedMenus);
        HashMap<String, Integer> menus = new HashMap<>();
        separatedMenus.forEach(separatedMenu -> {
            if (menus.containsKey(separatedMenu[MENU_POSITION])) {
                throw DuplicateMenuException.exception;
            }
            menus.put(separatedMenu[MENU_POSITION], validatedNumberParser(separatedMenu[COUNT_POSITION]));
        });
        return menus;
    }

    private int validatedNumberParser(String readLine) {
        try {
            return Integer.parseInt(readLine);
        } catch (NumberFormatException e) {
            throw DataTypeRangeException.exception;
        }
    }
}
