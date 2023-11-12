package christmas

import christmas.util.Validator.validateOrders
import christmas.util.Validator.validateVisitDay
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
        assertDoesNotThrow { validateVisitDay(input) }
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
}