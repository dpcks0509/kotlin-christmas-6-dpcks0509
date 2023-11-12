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

    fun printBenefitPreviewHeader(visitDay: Int) {
        println(OutputMessage.BENEFIT_PREVIEW_HEADER.getMessage().format(visitDay))
    }

    fun printOrdersHeader() {
        println(OutputMessage.ORDERS_HEADER.getMessage())
    }

    fun printOrders(orders: List<Order>) {
        orders.forEach { order -> println(order) }
    }

    fun printTotalOrderAmountBeforeDiscountHeader() {
        println(OutputMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_HEADER.getMessage())
    }

    fun printTotalOrderAmountBeforeDiscount(totalOrderAmountBeforeDiscount: Int) {
        println("${decimalFormat.format(totalOrderAmountBeforeDiscount)}원")
    }

    fun printGiftMenuHeader() {
        println(OutputMessage.GIFT_MENU_HEADER.getMessage())
    }

    fun printGiftMenu(gift: String) {
        println(gift)
    }

    fun printBenefitDetailsHeader() {
        println(OutputMessage.BENEFIT_DETAILS_HEADER.getMessage())
    }

    fun printDDayDiscount(dDayDiscount: Int) {
        if (dDayDiscount != 0)
            println(OutputMessage.D_DAY_DISCOUNT.getMessage().format(decimalFormat.format(dDayDiscount)))
    }

    fun printWeekendDayDiscount(weekendDayDiscount: Int) {
        if (weekendDayDiscount != 0)
            println(OutputMessage.WEEKEND_DAY_DISCOUNT.getMessage().format(decimalFormat.format(weekendDayDiscount)))
    }

    fun printWeekDayDiscount(weekDayDiscount: Int) {
        if (weekDayDiscount != 0)
            println(OutputMessage.WEEK_DAY_DISCOUNT.getMessage().format(decimalFormat.format(weekDayDiscount)))
    }

    fun printSpecialDayDiscount(specialDayDiscount: Int) {
        if (specialDayDiscount != 0)
            println(OutputMessage.SPECIAL_DAY_DISCOUNT.getMessage().format(decimalFormat.format(specialDayDiscount)))
    }

    fun printGiftBenefit(giftBenefit: Int) {
        if (giftBenefit != 0)
            println(OutputMessage.GIFT_BENEFIT.getMessage().format(decimalFormat.format(giftBenefit)))
    }

    fun printNoBenefit() {
        println(OutputMessage.NO_BENEFIT.getMessage())
    }

    fun printTotalBenefitAmountHeader() {
        println(OutputMessage.TOTAL_BENEFIT_AMOUNT_HEADER.getMessage())
    }

    fun printTotalBenefitAmount(totalBenefitAmount: Int) {
        if (totalBenefitAmount != 0) {
            println(OutputMessage.NEGATIVE_AMOUNT.getMessage().format(decimalFormat.format(totalBenefitAmount)))
        } else {
            println(OutputMessage.POSITIVE_AMOUNT.getMessage().format(decimalFormat.format(totalBenefitAmount)))
        }
    }

    fun printTotalOrderAmountAfterDiscountHeader() {
        println(OutputMessage.TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT_HEADER.getMessage())
    }

    fun printTotalOrderAmountAfterDiscount(totalOrderAmountAfterDiscount: Int) {
        println("${decimalFormat.format(totalOrderAmountAfterDiscount)}원")
    }

    fun printBadgeHeader() {
        println(OutputMessage.BADGE_HEADER.getMessage())
    }

    fun printBadge(badge: String) {
        println(badge)
    }
}