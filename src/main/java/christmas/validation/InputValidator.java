package christmas.validation;

import christmas.exception.IncludeCharException;
import christmas.exception.InputBlankException;

public class InputValidator {

    public void validateNumber(String readLine) {
        checkBlack(readLine);
        checkDigit(readLine);
    }

    private void checkDigit(String readLine) {
        for (int i = 0; i < readLine.length(); i++) {
            if (!Character.isDigit(readLine.charAt(i))) {
                throw IncludeCharException.exception;
            }
        }
    }

    private void checkBlack(String readLine) {
        if (readLine == null || readLine.isBlank()) {
            throw InputBlankException.exception;
        }
    }
}
