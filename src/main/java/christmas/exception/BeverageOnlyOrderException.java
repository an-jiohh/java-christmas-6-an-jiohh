package christmas.exception;

public class BeverageOnlyOrderException extends IllegalArgumentException {
    public static final BeverageOnlyOrderException exception = new BeverageOnlyOrderException();

    private BeverageOnlyOrderException() {
        super("[ERROR] 음료만 주문 할 수 없습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
