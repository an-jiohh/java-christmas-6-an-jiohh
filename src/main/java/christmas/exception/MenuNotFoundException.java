package christmas.exception;

public class MenuNotFoundException extends IllegalArgumentException {
    public static final MenuNotFoundException exception = new MenuNotFoundException();

    private MenuNotFoundException() {
        super("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
