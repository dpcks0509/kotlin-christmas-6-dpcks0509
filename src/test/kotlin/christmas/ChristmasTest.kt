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
    fun `방문 날짜 1 이상 31 이하의 숫자인 경우`(input: String) {
        assertDoesNotThrow { validateVisitDay(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "32", "", " 1", "a"])
    fun `방문 날짜 1 이상 31 이하의 숫자 아닌 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateVisitDay(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["양송이수프-1", "티본스테이크-2", "초코케이크-3"])
    fun `고객이 메뉴판에 있는 메뉴를 입력하는 경우ㅇ`(input: String) {
        assertDoesNotThrow { validateOrders(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["옥수수수프-1", "함박스테이크-2", "딸기케이크-3"])
    fun `고객이 메뉴판에 없는 메뉴를 입력하는 경우`(input: String) {
        assertThrows<IllegalArgumentException> { validateOrders(input) }
    }
}