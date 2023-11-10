package christmas.util

import christmas.util.Constants.EVENT_END_DAY
import christmas.util.Constants.EVENT_START_DAY

object Validator {
    fun validateVisitDayFormat(visitDay: String) =
        requireNotNull(visitDay.toIntOrNull()) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }

    fun validateVisitDayRange(visitDay: String) =
        require(visitDay.toInt() in EVENT_START_DAY..EVENT_END_DAY) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }
}