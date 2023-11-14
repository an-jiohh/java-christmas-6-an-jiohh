package christmas.exception;

public class MenuLimitExceededException extends IllegalArgumentException {
    public static final MenuLimitExceededException exception = new MenuLimitExceededException();

    private MenuLimitExceededException() {
        super("[ERROR] 메뉴는 20개까지 주문할 수 있습니다.");
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
