package christmas.validation;

import static christmas.constants.Constants.COUNT_POSITION;
import static christmas.constants.Constants.INITIAL_NUM;
import static christmas.constants.Constants.MENUS_SEPARATOR;
import static christmas.constants.Constants.MENUS_SEPARATOR_DUPLICATE;
import static christmas.constants.Constants.MENU_COUNT_SEPARATOR;
import static christmas.constants.Constants.MENU_COUNT_SEPARATOR_DUPLICATE;
import static christmas.constants.Constants.MENU_COUNT_SPLIT_LENGTH;
import static christmas.constants.Constants.MENU_POSITION;

import christmas.exception.CountIncludeCharException;
import christmas.exception.DateIncludeCharException;
import christmas.exception.InputBlankException;
import christmas.exception.InvalidMenuCountSeparatorException;
import christmas.exception.InvalidMenusSeparatorException;
import java.util.List;

public class InputValidator {

    public void validateNumber(String readLine) {
        checkBlack(readLine);
        checkDateDigit(readLine);
    }

    public void validateMenuLine(String readLine) {
        checkBlack(readLine);
        checkMenusSeparator(readLine);
    }

    public void validateMenuCountSplit(List<String> menus) {
        menus.forEach(menuAndCount -> {
            checkMenuCountContainSeparator(menuAndCount);
            checkMenuCountSeparatorPosition(menuAndCount);
            checkMenuCountSplitPosition(menuAndCount);
        });
    }

    public void validateMenu(List<String[]> menus) {
        menus.forEach(menu -> checkBlack(menu[MENU_POSITION]));
    }

    public void validateCount(List<String[]> menus) {
        menus.forEach(menu -> {
            checkBlack(menu[COUNT_POSITION]);
            checkCountDigit(menu[COUNT_POSITION]);
        });
    }

    private void checkDateDigit(String readLine) {
        for (int i = 0; i < readLine.length(); i++) {
            if (!Character.isDigit(readLine.charAt(i))) {
                throw DateIncludeCharException.exception;
            }
        }
    }

    private void checkCountDigit(String readLine) {
        for (int i = 0; i < readLine.length(); i++) {
            if (!Character.isDigit(readLine.charAt(i))) {
                throw CountIncludeCharException.exception;
            }
        }
    }

    private void checkBlack(String readLine) {
        if (readLine == null || readLine.isBlank()) {
            throw InputBlankException.exception;
        }
    }

    private void checkMenusSeparator(String readLine) {
        if (MENUS_SEPARATOR.charAt(INITIAL_NUM) == readLine.charAt(INITIAL_NUM)) {
            throw InvalidMenusSeparatorException.exception;
        }
        if (MENUS_SEPARATOR.charAt(INITIAL_NUM) == readLine.charAt(readLine.length() - 1)) {
            throw InvalidMenusSeparatorException.exception;
        }
        if (readLine.contains(MENUS_SEPARATOR_DUPLICATE)) {
            throw InvalidMenusSeparatorException.exception;
        }
    }

    private void checkMenuCountContainSeparator(String menuAndCount) {
        if (!menuAndCount.contains(MENU_COUNT_SEPARATOR)) {
            throw InvalidMenuCountSeparatorException.exception;
        }
    }

    private void checkMenuCountSeparatorPosition(String menuAndCount) {
        if (MENU_COUNT_SEPARATOR.charAt(INITIAL_NUM) == menuAndCount.charAt(INITIAL_NUM)) {
            throw InvalidMenuCountSeparatorException.exception;
        }
        if (MENU_COUNT_SEPARATOR.charAt(INITIAL_NUM) == menuAndCount.charAt(menuAndCount.length() - 1)) {
            throw InvalidMenuCountSeparatorException.exception;
        }
        if (menuAndCount.contains(MENU_COUNT_SEPARATOR_DUPLICATE)) {
            throw InvalidMenuCountSeparatorException.exception;
        }
    }

    private void checkMenuCountSplitPosition(String menuAndCount) {
        String[] split = menuAndCount.split(MENU_COUNT_SEPARATOR);
        if (split.length != MENU_COUNT_SPLIT_LENGTH) {
            throw InvalidMenuCountSeparatorException.exception;
        }
    }
}
