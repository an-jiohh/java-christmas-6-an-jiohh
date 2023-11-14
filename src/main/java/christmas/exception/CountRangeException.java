package christmas.exception;

public class CountRangeException extends IllegalArgumentException {
    public static final CountRangeException exception = new CountRangeException();

    private CountRangeException() {
        super("[ERROR] 메뉴 개수는 1이상을 입력해야합니다.");
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
