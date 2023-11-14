package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class CountRangeException extends IllegalArgumentException {
    public static final CountRangeException exception = new CountRangeException();

    private CountRangeException() {
        super(MENU_EXCEPTION_MESSAGE);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
