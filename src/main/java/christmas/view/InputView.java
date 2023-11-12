package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.DataTypeRangeException;
import christmas.validation.InputValidator;

public class InputView {

    private final InputValidator inputValidator;

    public InputView() {
        this.inputValidator = new InputValidator();
    }

    public int readNumberData() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String readLine = Console.readLine();
        inputValidator.validateNumber(readLine);
        return validatedNumberParser(readLine);
    }
    
    private int validatedNumberParser(String readLine) {
        try {
            return Integer.parseInt(readLine);
        } catch (NumberFormatException e) {
            throw DataTypeRangeException.exception;
        }
    }
}
