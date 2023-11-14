package christmas.exception;

import static christmas.constants.ExceptionMessage.MENU_EXCEPTION_MESSAGE;

public class MenuLimitExceededException extends IllegalArgumentException {
    public static final MenuLimitExceededException exception = new MenuLimitExceededException();

    private MenuLimitExceededException() {
        super(MENU_EXCEPTION_MESSAGE);
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
