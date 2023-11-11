package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Order
import christmas.util.Validator.validateOrders
import christmas.util.Validator.validateVisitDay

class InputView {
    fun inputVisitDay(): Int {
        val visitDay = Console.readLine()
        return validateVisitDay(visitDay)
    }

    fun inputOrders(): List<Order> {
        val orders = Console.readLine()
        return validateOrders(orders)
    }
}