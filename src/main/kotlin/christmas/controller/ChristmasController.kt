package christmas.controller

import christmas.model.Order
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val visitDay = getInputWithValidation { getVisitDay() }
        val orders = getInputWithValidation { getOrders() }
        getBenefitPreview(orders)
    }

    fun <T> getInputWithValidation(inputFunction: () -> T): T {
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

    private fun getBenefitPreview(orders: List<Order>) {
        outputView.printBenefitPreviewInstruction()
        outputView.printOrders(orders)
    }
}