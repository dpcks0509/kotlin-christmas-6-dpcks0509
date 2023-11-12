package christmas

import christmas.util.Validator.validateVisitDay
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasTest {
    @ParameterizedTest
    @ValueSource(strings = ["0", "32", "", " 1", "a"])
    fun `방문 날짜 1 이상 31 이하의 숫자`(input: String) {
        assertThrows<IllegalArgumentException> { validateVisitDay(input) }
    }
}