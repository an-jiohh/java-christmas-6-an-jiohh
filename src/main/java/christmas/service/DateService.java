package christmas.service;

import christmas.domain.Date;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DateService {
    private final InputView inputView;
    private final OutputView outputView;

    public DateService() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public Date fetchValidatedDate() {
        while (true) {
            try {
                int number = inputView.readNumberData();
                return new Date(number);
            } catch (IllegalArgumentException exception) {
                outputView.printDateErrorMessage();
            }
        }
    }
}
