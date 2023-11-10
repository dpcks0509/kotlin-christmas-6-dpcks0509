package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.util.Validator.validateVisitDayFormat
import christmas.util.Validator.validateVisitDayRange

class InputView {
    fun inputVisitDay(): Int {
        val visitDay = Console.readLine()
        validateVisitDayFormat(visitDay)
        validateVisitDayRange(visitDay)
        return visitDay.toInt()
    }
}