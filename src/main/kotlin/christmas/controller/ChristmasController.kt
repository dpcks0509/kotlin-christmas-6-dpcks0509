package christmas.controller

import christmas.model.Benefit
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
        val benefit = Benefit(visitDay, orders)
        getGiftMenu(benefit.getGift())
        getBenefitDetails(benefit)
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
        return orders.sumOf { order -> order.getOrderAmount() }
    }

    private fun getGiftMenu(gift: String) {
        outputView.printGiftMenu(gift)
    }

    private fun getBenefitDetails(benefit: Benefit) {
        outputView.printBenefitDetailsHeader()
        if (benefit.getTotalBenefitAmount() != 0) {
            outputView.printDDayDiscount(benefit.getDDayDiscount())
            outputView.printWeekendDayDiscount(benefit.getWeekendDayDiscount())
            outputView.printWeekDayDiscount(benefit.getWeekDayDiscount())
            outputView.printSpecialDayDiscount(benefit.getSpecialDayDiscount())
            outputView.printGiftBenefit(benefit.getGiftBenefit())
        } else {
            outputView.printNoBenefit()
        }
    }
}