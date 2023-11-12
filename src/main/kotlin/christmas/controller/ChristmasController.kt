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
        val benefit = Benefit(visitDay, orders)
        getTotalOrderAmountBeforeDiscount(benefit.getTotalOrderAmountBeforeDiscount())
        getGiftMenu(benefit.getGift())
        getBenefitDetails(benefit)
        getTotalBenefitAmount(benefit.getTotalBenefitAmount())
        getTotalOrderAmountAfterDiscount(benefit.getTotalOrderAmountAfterDiscount())
        getBadge(benefit.getBadge())
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
        outputView.printBenefitPreviewHeader(visitDay)
        outputView.printOrdersHeader()
        outputView.printOrders(orders)
    }

    private fun getTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount: Int) {
        outputView.printTotalOrderAmountBeforeDiscountHeader()
        outputView.printTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount)
    }

    private fun getGiftMenu(gift: String) {
        outputView.printGiftMenuHeader()
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

    private fun getTotalBenefitAmount(totalBenefitAmount: Int) {
        outputView.printTotalBenefitAmountHeader()
        outputView.printTotalBenefitAmount(totalBenefitAmount)
    }

    private fun getTotalOrderAmountAfterDiscount(totalOrderAmountAfterDiscount: Int) {
        outputView.printTotalOrderAmountAfterDiscountHeader()
        outputView.printTotalOrderAmountAfterDiscount(totalOrderAmountAfterDiscount)
    }

    private fun getBadge(badge: String) {
        outputView.printBadgeHeader()
        outputView.printBadge(badge)
    }
}