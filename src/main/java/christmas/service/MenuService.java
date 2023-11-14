package christmas.service;

import christmas.domain.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class MenuService {
    private final InputView inputView;
    private final OutputView outputView;

    public MenuService() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public Menu fetchValidatedMenu() {
        while (true) {
            try {
                HashMap<String, Integer> menus = inputView.readMenuData();
                return new Menu(menus);
            } catch (IllegalArgumentException exception) {
                outputView.printMenuErrorMessage();
            }
        }
    }
}
