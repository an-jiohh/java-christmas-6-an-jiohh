package christmas.exception;

public class InvalidMenuCountSeparatorException extends IllegalArgumentException {
    public static final InvalidMenuCountSeparatorException exception = new InvalidMenuCountSeparatorException();

    private InvalidMenuCountSeparatorException() {
        super("[ERROR] 메뉴와 메뉴 개수 구분이 잘못 되었습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
