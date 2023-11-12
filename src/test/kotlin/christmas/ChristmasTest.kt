package christmas

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
}