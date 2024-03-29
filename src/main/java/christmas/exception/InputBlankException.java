package christmas.exception;

public class InputBlankException extends IllegalArgumentException {
    public static final InputBlankException exception = new InputBlankException();

    private InputBlankException() {
        super("[ERROR] 빈칸이 입력되었습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
