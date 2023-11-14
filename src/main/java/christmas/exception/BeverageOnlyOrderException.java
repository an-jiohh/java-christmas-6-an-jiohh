package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class BeverageOnlyOrderException extends IllegalArgumentException {
    public static final BeverageOnlyOrderException exception = new BeverageOnlyOrderException();

    private BeverageOnlyOrderException() {
        super(MENU_EXCEPTION_MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
