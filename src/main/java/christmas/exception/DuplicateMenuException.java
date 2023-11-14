package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class DuplicateMenuException extends IllegalArgumentException {
    public static final DuplicateMenuException exception = new DuplicateMenuException();

    private DuplicateMenuException() {
        super(MENU_EXCEPTION_MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
