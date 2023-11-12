package christmas.exception;

public class MenuNotFoundException extends IllegalArgumentException {
    public static final MenuNotFoundException exception = new MenuNotFoundException();

    private MenuNotFoundException() {
        super("[ERROR] 메뉴가 존재하지 않습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
