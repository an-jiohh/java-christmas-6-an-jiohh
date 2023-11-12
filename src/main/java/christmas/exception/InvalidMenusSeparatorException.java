package christmas.exception;

public class InvalidMenusSeparatorException extends IllegalArgumentException {
    public static final InvalidMenusSeparatorException exception = new InvalidMenusSeparatorException();

    private InvalidMenusSeparatorException() {
        super("[ERROR] 메뉴 구분이 잘못 되었습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
