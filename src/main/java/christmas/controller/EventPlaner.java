package christmas.controller;

import static christmas.constants.OutputMessage.START_HEAD;

import christmas.domain.Date;
import christmas.domain.Discounts;
import christmas.domain.Menu;
import christmas.domain.TodayBenefits;
import christmas.service.DateService;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.service.TodayBenefitService;
import christmas.view.OutputView;

public class EventPlaner {
    private OutputView outputView;

    public EventPlaner() {
        this.outputView = new OutputView();
    }

    public void startPlaner() {
        outputView.printMessage(START_HEAD);
        Date date = createDate();
        Menu menu = createMenus();
        TodayBenefits todayBenefits = calculateTodayBenefits(date);
        Discounts discounts = calculateDiscount(date, menu, todayBenefits);
        printResult(date, menu, discounts);
    }

    private Date createDate() {
        DateService dateService = new DateService();
        Date date = dateService.fetchValidatedDate();
        return date;
    }

    private Menu createMenus() {
        MenuService menuService = new MenuService();
        Menu menu = menuService.fetchValidatedMenu();
        return menu;
    }

    private TodayBenefits calculateTodayBenefits(Date date) {
        TodayBenefitService todayBenefitService = new TodayBenefitService(date);
        TodayBenefits todayBenefits = todayBenefitService.getTodayEvents();
        return todayBenefits;
    }

    private Discounts calculateDiscount(Date date, Menu menu, TodayBenefits todayEvents) {
        DiscountService discountService = new DiscountService(date, menu, todayEvents);
        Discounts discounts = discountService.calculateDiscount();
        return discounts;
    }

    private void printResult(Date date, Menu menu, Discounts discounts) {
        outputView.printResultHead(date);
        outputView.printAllMenu(menu);
        outputView.printBeforeDiscountPrice(menu);
        outputView.printGiftMenu(discounts);
        outputView.printAllDiscount(discounts);
        outputView.printAllBenefitsAmount(discounts);
        outputView.printPaymentAmount(menu, discounts);
        outputView.printEventBadge(discounts);
    }
}
