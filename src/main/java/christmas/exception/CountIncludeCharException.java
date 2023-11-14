package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class CountIncludeCharException extends IllegalArgumentException {
    public static final CountIncludeCharException exception = new CountIncludeCharException();

    private CountIncludeCharException() {
        super(MENU_EXCEPTION_MESSAGE);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
