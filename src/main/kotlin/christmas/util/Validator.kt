package christmas.util

import christmas.util.Constants.EVENT_END_DAY
import christmas.util.Constants.EVENT_START_DAY

object Validator {
    fun validateVisitDay(visitDay: String): Int {
        val validVisitDay = visitDay.toIntOrNull()
        validateVisitDayFormat(validVisitDay)
        validateVisitDayRange(validVisitDay!!)
        return validVisitDay
    }

    private fun validateVisitDayFormat(validVisitDay: Int?) =
        requireNotNull(validVisitDay) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }

    private fun validateVisitDayRange(validVisitDay: Int) =
        require(validVisitDay in EVENT_START_DAY..EVENT_END_DAY) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }
}