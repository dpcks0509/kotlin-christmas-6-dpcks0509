package christmas.view

import christmas.model.Order
import christmas.util.OutputMessage

class OutputView {
    fun printErrorMessage(errorMessage: String) {
        println(errorMessage)
    }

    fun printVisitDayInstruction() {
        println(OutputMessage.VISIT_DAY_INSTRUCTION.getMessage())
    }

    fun printOrderMenusInstruction() {
        println(OutputMessage.ORDER_MENUS_INSTRUCTION.getMessage())
    }

    fun printBenefitPreviewInstruction() {
        println(OutputMessage.BENEFIT_PREVIEW_HEADER.getMessage())
    }

    fun printOrders(orders: List<Order>) {
        println(OutputMessage.ORDERS_HEADER.getMessage())
        orders.forEach { order -> println(order) }
    }
}