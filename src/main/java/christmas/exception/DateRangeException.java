package christmas.exception;

import static christmas.constants.ExceptionMessage.DATE_EXCEPTION_MESSAGE;

public class DateRangeException extends IllegalArgumentException {
    public static final DateRangeException exception = new DateRangeException();

    private DateRangeException() {
        super(DATE_EXCEPTION_MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
