package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Menu
import christmas.util.Validator.validateVisitDay

class InputView {
    fun inputVisitDay(): Int {
        val visitDay = Console.readLine()
        return validateVisitDay(visitDay)
    }
}