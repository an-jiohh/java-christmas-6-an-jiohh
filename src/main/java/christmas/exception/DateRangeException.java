package christmas.exception;

public class DateRangeException extends IllegalArgumentException {
    public static final DateRangeException exception = new DateRangeException();

    private DateRangeException() {
        super("[ERROR] 12월은 1일에서 31일까지 존재합니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
