package christmas.view

import christmas.model.Order
import christmas.util.OutputMessage
import java.text.DecimalFormat

class OutputView {
    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }

    fun printErrorMessage(errorMessage: String) {
        println(errorMessage)
    }

    fun printVisitDayInstruction() {
        println(OutputMessage.VISIT_DAY_INSTRUCTION.getMessage())
    }

    fun printOrderMenusInstruction() {
        println(OutputMessage.ORDER_MENUS_INSTRUCTION.getMessage())
    }

    fun printBenefitPreviewInstruction(visitDay: Int) {
        println(OutputMessage.BENEFIT_PREVIEW_INSTUCTION.getMessage().format(visitDay))
    }

    fun printOrders(orders: List<Order>) {
        println(OutputMessage.ORDERS_HEADER.getMessage())
        orders.forEach { order -> println(order) }
    }

    fun printTotalOrderAmountBeforeDiscount(totalOrderAmount: Int) {
        println(OutputMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_HEADER.getMessage())
        println("${decimalFormat.format(totalOrderAmount)}원")
    }
}