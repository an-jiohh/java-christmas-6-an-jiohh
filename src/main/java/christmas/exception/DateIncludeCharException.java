package christmas.exception;

import static christmas.constants.ExceptionMessage.DATE_EXCEPTION_MESSAGE;

public class DateIncludeCharException extends IllegalArgumentException {
    public static final DateIncludeCharException exception = new DateIncludeCharException();

    private DateIncludeCharException() {
        super(DATE_EXCEPTION_MESSAGE);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
