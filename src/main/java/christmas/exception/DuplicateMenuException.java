package christmas.exception;

public class DuplicateMenuException extends IllegalArgumentException {
    public static final DuplicateMenuException exception = new DuplicateMenuException();

    private DuplicateMenuException() {
        super("[ERROR] 중복된 메뉴가 존재합니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
