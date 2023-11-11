package christmas.controller

import christmas.model.Menu
import christmas.model.Order
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val visitDay = getInputWithValidation { getVisitDay() }
        val orders = getInputWithValidation { getOrders() }
        getBenefitPreview(visitDay, orders)
        val totalOrderAmountBeforeDiscount = calculateTotalOrderAmountBeforeDiscount(orders)
        getTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount)
    }

    private fun <T> getInputWithValidation(inputFunction: () -> T): T {
        return try {
            inputFunction()
        } catch (illegalArgumentException: IllegalArgumentException) {
            outputView.printErrorMessage(illegalArgumentException.message.toString())
            getInputWithValidation(inputFunction)
        }
    }

    private fun getVisitDay(): Int {
        outputView.printVisitDayInstruction()
        return inputView.inputVisitDay()
    }

    private fun getOrders(): List<Order> {
        outputView.printOrderMenusInstruction()
        return inputView.inputOrders()
    }

    private fun getBenefitPreview(visitDay: Int, orders: List<Order>) {
        outputView.printBenefitPreviewInstruction(visitDay)
        outputView.printOrders(orders)
    }

    private fun getTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount: Int) {
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount)
    }

    private fun calculateTotalOrderAmountBeforeDiscount(orders: List<Order>): Int {
        return orders.sumOf { order -> order.calculateOrderAmount(order) }
    }
}