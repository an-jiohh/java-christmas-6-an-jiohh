package christmas.domain;

import static christmas.constants.Event.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.constants.Event.GIFT_EVENTS;
import static christmas.constants.Event.SPECIAL_DISCOUNT;
import static christmas.constants.Event.WEEKDAY_DISCOUNT;

import christmas.constants.Event;
import christmas.constants.EventBadge;
import java.util.HashMap;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiscountsTest {

    private static HashMap<Event, Integer> createFirstTestCase() {
        HashMap<Event, Integer> firstTestcase = new HashMap<>();
        firstTestcase.put(CHRISTMAS_D_DAY_DISCOUNT, CHRISTMAS_D_DAY_DISCOUNT.getDiscount());
        firstTestcase.put(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getDiscount());
        firstTestcase.put(WEEKDAY_DISCOUNT, WEEKDAY_DISCOUNT.getDiscount());
        firstTestcase.put(GIFT_EVENTS, GIFT_EVENTS.getDiscount());
        return firstTestcase;
    }

    private static HashMap<Event, Integer> secondFirstTestCase() {
        HashMap<Event, Integer> secondTestCase = new HashMap<>();
        secondTestCase.put(CHRISTMAS_D_DAY_DISCOUNT, CHRISTMAS_D_DAY_DISCOUNT.getDiscount());
        secondTestCase.put(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getDiscount());
        secondTestCase.put(Event.WEEKEND_DISCOUNT, WEEKDAY_DISCOUNT.getDiscount() * 2);
        return secondTestCase;
    }

    @ParameterizedTest
    @DisplayName("모든 혜택을 더하는 기능")
    @MethodSource
    void sumAllBenefitsAmountTest(HashMap<Event, Integer> testCase, int expect) {
        Discounts discounts = new Discounts(testCase);
        Assertions.assertThat(discounts.sumAllBenefitsAmount()).isEqualTo(expect);
    }

    private static Stream<Arguments> sumAllBenefitsAmountTest() {
        int firstExpect = CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + SPECIAL_DISCOUNT.getDiscount()
                + WEEKDAY_DISCOUNT.getDiscount() + GIFT_EVENTS.getDiscount();
        int secondExpect = CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + SPECIAL_DISCOUNT.getDiscount() + (
                WEEKDAY_DISCOUNT.getDiscount() * 2);
        return Stream.of(
                Arguments.of(createFirstTestCase(), firstExpect),
                Arguments.of(secondFirstTestCase(), secondExpect)
        );
    }

    @ParameterizedTest
    @DisplayName("모든 할인 금액을 더하는 기능(선물제외)")
    @MethodSource
    void sumAllDiscountAmountTest(HashMap<Event, Integer> testCase, int expect) {
        Discounts discounts = new Discounts(testCase);
        Assertions.assertThat(discounts.sumAllDiscountAmount()).isEqualTo(expect);
    }

    private static Stream<Arguments> sumAllDiscountAmountTest() {
        int firstExpect = CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + SPECIAL_DISCOUNT.getDiscount()
                + WEEKDAY_DISCOUNT.getDiscount();
        int secondExpect = CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + SPECIAL_DISCOUNT.getDiscount() + (
                WEEKDAY_DISCOUNT.getDiscount() * 2);
        return Stream.of(
                Arguments.of(createFirstTestCase(), firstExpect),
                Arguments.of(secondFirstTestCase(), secondExpect)
        );
    }

    @ParameterizedTest
    @DisplayName("혜택이 존재하는지 확인하는 기능")
    @MethodSource
    void isContainEventTest(HashMap<Event, Integer> testCase, Event target, boolean expect) {
        Discounts discounts = new Discounts(testCase);
        Assertions.assertThat(discounts.isContainEvent(target)).isEqualTo(expect);
    }

    private static Stream<Arguments> isContainEventTest() {
        return Stream.of(
                Arguments.of(createFirstTestCase(), WEEKDAY_DISCOUNT, true),
                Arguments.of(secondFirstTestCase(), GIFT_EVENTS, false)
        );
    }

    @ParameterizedTest
    @DisplayName("혜택이 비어있는지 확인하는 기능")
    @MethodSource
    void isDiscountEmptyTest(HashMap<Event, Integer> testCase, boolean expect) {
        Discounts discounts = new Discounts(testCase);
        Assertions.assertThat(discounts.isDiscountEmpty()).isEqualTo(expect);
    }

    private static Stream<Arguments> isDiscountEmptyTest() {
        return Stream.of(
                Arguments.of(createFirstTestCase(), false),
                Arguments.of(secondFirstTestCase(), false),
                Arguments.of(new HashMap<Event, Integer>(), true)
        );
    }

    @ParameterizedTest
    @DisplayName("이벤트 배지 생성 기능")
    @MethodSource
    void createEventBadgeTest(HashMap<Event, Integer> testCase, EventBadge expect) {
        Discounts discounts = new Discounts(testCase);
        Assertions.assertThat(discounts.createEventBadge()).isEqualTo(expect);
    }

    private static Stream<Arguments> createEventBadgeTest() {
        return Stream.of(
                Arguments.of(createFirstTestCase(), EventBadge.SANTA),
                Arguments.of(secondFirstTestCase(), EventBadge.STAR)
        );
    }
}