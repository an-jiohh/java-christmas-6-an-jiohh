package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class InvalidMenuCountSeparatorException extends IllegalArgumentException {
    public static final InvalidMenuCountSeparatorException exception = new InvalidMenuCountSeparatorException();

    private InvalidMenuCountSeparatorException() {
        super(MENU_EXCEPTION_MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
