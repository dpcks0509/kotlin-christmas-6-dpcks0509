package christmas.model

import christmas.util.NumericConstants.MINIMUM_BENEFIT_AMOUNT
import christmas.util.NumericConstants.NO_BENEFIT_AMOUNT
import christmas.util.StringConstants.NO_BENEFIT

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private var totalOrderAmountBeforeDiscount = 0
    private var totalBenefitAmount = 0
    private var totalOrderAmountAfterDiscount = 0

    private val discount = Discount(visitDay, orders)
    private var gift = Gift(NO_BENEFIT, NO_BENEFIT_AMOUNT)
    private var badge = Badge.NO_BADGE

    init {
        totalOrderAmountBeforeDiscount = initializeTotalOrderAmountBeforeDiscount()
        if (totalOrderAmountBeforeDiscount >= MINIMUM_BENEFIT_AMOUNT) {
            initializeDiscounts()
            gift = initializeGift(totalOrderAmountBeforeDiscount)
            totalBenefitAmount = initializeTotalBenefitAmount()
            totalOrderAmountAfterDiscount = initializeTotalOrderAmountAfterDiscount()
            badge = initializeBadge(totalBenefitAmount)
        }
    }

    private fun initializeTotalOrderAmountBeforeDiscount(): Int {
        return orders.sumOf { order -> order.getOrderAmount() }
    }

    private fun initializeDiscounts() {
        discount.initializeDiscounts()
    }

    private fun initializeGift(totalOrderAmountBeforeDiscount: Int): Gift {
        return gift.initializeGift(totalOrderAmountBeforeDiscount)
    }

    private fun initializeTotalBenefitAmount(): Int {
        return discount.getDDayDiscount() + discount.getWeekendDayDiscount() + discount.getWeekDayDiscount() +
                discount.getSpecialDayDiscount() + gift.getBenefitAmount()
    }

    private fun initializeTotalOrderAmountAfterDiscount(): Int {
        return totalOrderAmountBeforeDiscount - totalBenefitAmount + gift.getBenefitAmount()
    }

    private fun initializeBadge(totalBenefitAmount: Int): Badge {
        return badge.initializeBadge(totalBenefitAmount)
    }

    fun getTotalOrderAmountBeforeDiscount(): Int = totalOrderAmountBeforeDiscount

    fun getTotalBenefitAmount(): Int = totalBenefitAmount

    fun getTotalOrderAmountAfterDiscount(): Int = totalOrderAmountAfterDiscount

    fun getDiscount(): Discount = discount

    fun getGift(): Gift = gift

    fun getBadge(): Badge = badge
}