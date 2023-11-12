package christmas.model

import christmas.util.NumericConstants
import christmas.util.NumericConstants.BASIC_D_DAY_DISCOUNT_AMOUNT
import christmas.util.NumericConstants.D_DAY_EVENT_END_DAY
import christmas.util.NumericConstants.EVENT_START_DAY
import christmas.util.NumericConstants.GIFT_AMOUNT
import christmas.util.NumericConstants.MINIMUM_BENEFIT_AMOUNT
import christmas.util.NumericConstants.MINIMUM_GIFT_AMOUNT
import christmas.util.NumericConstants.NO_BENEFIT_AMOUNT
import christmas.util.NumericConstants.SPECIAL_DAY_DISCOUNT_AMOUNT
import christmas.util.NumericConstants.STEP_D_DAY_DISCOUNT_AMOUNT
import christmas.util.NumericConstants.WEEK_DISCOUNT_AMOUNT
import christmas.util.StringConstants
import christmas.util.StringConstants.DESSERT
import christmas.util.StringConstants.GIFT_CHAMPAGNE
import christmas.util.StringConstants.MAIN
import christmas.util.StringConstants.NO_BENEFIT

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private val weekendDays = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    private val specialDays = listOf(3, 10, 17, 24, 25, 31)

    private var totalOrderAmountBeforeDiscount = 0
    private var totalBenefitAmount = 0
    private var totalOrderAmountAfterDiscount = 0

    private var dDayDiscount = 0
    private var weekendDayDiscount = 0
    private var weekDayDiscount = 0
    private var specialDayDiscount = 0

    private var gift = Gift(NO_BENEFIT, NO_BENEFIT_AMOUNT)

    private var badge = Badge.NO_BADGE

    init {
        totalOrderAmountBeforeDiscount = initializeTotalOrderAmountBeforeDiscount()
        if (totalOrderAmountBeforeDiscount >= MINIMUM_BENEFIT_AMOUNT) {
            initializeDiscounts()
            if (totalOrderAmountBeforeDiscount >= MINIMUM_GIFT_AMOUNT) {
                gift = initializeGift()
            }
            totalBenefitAmount = initializeTotalBenefitAmount()
            totalOrderAmountAfterDiscount = initializeTotalOrderAmountAfterDiscount()
            badge = initializeBadge(totalBenefitAmount)
        }
    }

    private fun initializeTotalOrderAmountBeforeDiscount(): Int {
        return orders.sumOf { order -> order.getOrderAmount() }
    }

    private fun initializeTotalOrderAmountAfterDiscount(): Int {
        return totalOrderAmountBeforeDiscount - totalBenefitAmount + gift.getBenefitAmount()
    }

    private fun initializeDiscounts() {
        if (visitDay in EVENT_START_DAY..D_DAY_EVENT_END_DAY) {
            dDayDiscount = benefitDDayDiscount(visitDay)
        }
        if (weekendDays.contains(visitDay)) {
            val numberOfMain = getNumberOfMain()
            weekendDayDiscount = benefitWeekendDayDiscount(numberOfMain)
        } else {
            val numberOfDessert = getNumberOfDessert()
            weekDayDiscount = benefitWeekDayDiscount(numberOfDessert)
        }
        if (specialDays.contains(visitDay)) {
            specialDayDiscount = benefitSpecialDayDiscount()
        }
    }

    private fun initializeGift(): Gift {
        return gift.initializeGift()
    }

    private fun initializeBadge(totalBenefitAmount: Int): Badge {
        return badge.initializeBadge(totalBenefitAmount)
    }

    private fun benefitDDayDiscount(visitDay: Int): Int {
        return BASIC_D_DAY_DISCOUNT_AMOUNT + (visitDay - 1) * STEP_D_DAY_DISCOUNT_AMOUNT
    }

    private fun benefitWeekendDayDiscount(numberOfMain: Int): Int {
        return numberOfMain * WEEK_DISCOUNT_AMOUNT
    }

    private fun getNumberOfMain(): Int {
        var numberOfMain = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == MAIN) {
                numberOfMain += order.getOrderQuantity()
            }
        }
        return numberOfMain
    }

    private fun benefitWeekDayDiscount(numberOfDessert: Int): Int {
        return numberOfDessert * WEEK_DISCOUNT_AMOUNT
    }

    private fun getNumberOfDessert(): Int {
        var numberOfDessert = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == DESSERT) {
                numberOfDessert += order.getOrderQuantity()
            }
        }
        return numberOfDessert
    }

    private fun benefitSpecialDayDiscount(): Int {
        return SPECIAL_DAY_DISCOUNT_AMOUNT
    }

    private fun initializeTotalBenefitAmount(): Int {
        return dDayDiscount + weekendDayDiscount + weekDayDiscount + specialDayDiscount + gift.getBenefitAmount()
    }

    fun getTotalOrderAmountBeforeDiscount(): Int = totalOrderAmountBeforeDiscount

    fun getTotalOrderAmountAfterDiscount(): Int = totalOrderAmountAfterDiscount

    fun getTotalBenefitAmount(): Int = totalBenefitAmount

    fun getDDayDiscount(): Int = dDayDiscount

    fun getWeekendDayDiscount(): Int = weekendDayDiscount

    fun getWeekDayDiscount(): Int = weekDayDiscount

    fun getSpecialDayDiscount(): Int = specialDayDiscount

    fun getGift(): Gift = gift

    fun getBadge(): Badge = badge
}