package christmas.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum Event {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 900, 100, List.of(), createRangeNumbers(1, 25)),
    WEEKDAY_DISCOUNT("평일 할인", 2023, List.of(Week.SUNDAY, Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY),
            List.of()),
    WEEKEND_DISCOUNT("주말 할인", 2023, List.of(Week.FRIDAY, Week.SATURDAY), List.of()),
    SPECIAL_DISCOUNT("특별 할인", 1000, List.of(Week.SUNDAY), List.of(25)),
    GIFT_EVENTS("증정 이벤트", 25000, List.of(Week.values()), createRangeNumbers(1, 31));

    private String name;
    private int discount;
    private int incrementDiscount;
    private List<Week> targetWeek;
    private List<Integer> targetDay;

    Event(String name, int discount, List<Week> targetWeek, List<Integer> targetDay) {
        this.name = name;
        this.discount = discount;
        this.targetWeek = targetWeek;
        this.targetDay = targetDay;
    }

    Event(String name, int discount, int incrementDiscount, List<Week> targetWeek, List<Integer> targetDay) {
        this.name = name;
        this.discount = discount;
        this.incrementDiscount = incrementDiscount;
        this.targetWeek = targetWeek;
        this.targetDay = targetDay;
    }

    private static List<Integer> createRangeNumbers(int start, int end) {
        return Arrays.stream(IntStream.rangeClosed(start, end).toArray()).boxed().toList();
    }

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    public List<Week> getTargetWeek() {
        return targetWeek;
    }

    public List<Integer> getTargetDay() {
        return targetDay;
    }

    public int getIncrementDiscount() {
        return incrementDiscount;
    }
}