package christmas

import christmas.model.Discount
import christmas.model.Order
import christmas.util.Validator.validateOrders
import christmas.util.Validator.validateVisitDay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "25", "31"])
    fun `올바른 방문 날짜 입력`(input: String) {
        assertDoesNotThrow { validateVisitDay(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "32", "", " 1", "a"])
    fun `방문 날짜 1 이상 31 이하의 숫자 아닌 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateVisitDay(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"])
    fun `올바른 주문 메뉴, 개수 입력`(input: String) {
        assertDoesNotThrow { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["옥수수수프-1", "함박스테이크-2", "딸기케이크-3"])
    fun `고객이 메뉴판에 없는 메뉴를 입력하는 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["양송이수프-0", "티본스테이크--1", "초코케이크-one"])
    fun `메뉴의 개수가 1 이상의 숫자가 아닌경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["양송이수프=1", "티본스테이크,2", "초코케이크- 3", "아이스크림 -4"])
    fun `메뉴 형식이 예시와 다른 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,제로콜라-1,타파스-2", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,티본스테이크-2"])
    fun `중복 메뉴를 입력한 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-1", "제로콜라-1,레드와인-2", "제로콜라-1,레드와인-2,샴페인-3"])
    fun `음료만 주문한 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-21", "시저샐러드-4, 티본스테이크-5, 크리스마스파스타-6, 제로콜라-7"])
    fun `한 번에 20개 초과 매뉴룰 주문한 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }

    @Test
    fun `크리스마스 디데이 할인 기간인 경우`() {
        val discount = Discount(25, listOf(Order("타파스", 1)))
        val expectDDayDiscount = 3400

        discount.initializeDiscounts()
        val actualDDayDiscount = discount.getDDayDiscount()

        assertThat(expectDDayDiscount).isEqualTo(actualDDayDiscount)
    }

    @Test
    fun `크리스마스 디데이 할인 기간아닌 경우`() {
        val discount = Discount(26, listOf(Order("타파스", 1)))
        val expectDDayDiscount = 3500

        discount.initializeDiscounts()
        val actualDDayDiscount = discount.getDDayDiscount()

        assertThat(expectDDayDiscount).isNotEqualTo(actualDDayDiscount)
    }

    @Test
    fun `평일 할인 디저트 메뉴일 경우`() {
        val discount = Discount(11, listOf(Order("초코케이크", 2)))
        val expectWeekDayDiscount = 4046

        discount.initializeDiscounts()
        val actualWeekDayDiscount = discount.getWeekDayDiscount()

        assertThat(expectWeekDayDiscount).isEqualTo(actualWeekDayDiscount)
    }

    @Test
    fun `평일 할인 디저트 메뉴아닐 경우`() {
        val discount = Discount(11, listOf(Order("티본스테이크", 2)))
        val expectWeekDayDiscount = 4046

        discount.initializeDiscounts()
        val actualWeekDayDiscount = discount.getWeekDayDiscount()

        assertThat(expectWeekDayDiscount).isNotEqualTo(actualWeekDayDiscount)
    }

    @Test
    fun `주말 할인 메인 메뉴일 경우`() {
        val discount = Discount(15, listOf(Order("티본스테이크", 2)))
        val expectWeekendDayDiscount = 4046

        discount.initializeDiscounts()
        val actualWeekendDayDiscount = discount.getWeekendDayDiscount()

        assertThat(expectWeekendDayDiscount).isEqualTo(actualWeekendDayDiscount)
    }

    @Test
    fun `주말 할인 메인 메뉴아닐 경우`() {
        val discount = Discount(15, listOf(Order("초코케이크", 2)))
        val expectWeekendDayDiscount = 4046

        discount.initializeDiscounts()
        val actualWeekendDayDiscount = discount.getWeekendDayDiscount()

        assertThat(expectWeekendDayDiscount).isNotEqualTo(actualWeekendDayDiscount)
    }
}