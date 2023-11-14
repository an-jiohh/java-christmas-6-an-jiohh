package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class InvalidMenusSeparatorException extends IllegalArgumentException {
    public static final InvalidMenusSeparatorException exception = new InvalidMenusSeparatorException();

    private InvalidMenusSeparatorException() {
        super(MENU_EXCEPTION_MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
