package christmas.model

import christmas.util.Constants.BASIC_D_DAY_DISCOUNT_AMOUNT
import christmas.util.Constants.D_DAY_EVENT_END_DAY
import christmas.util.Constants.EVENT_START_DAY
import christmas.util.Constants.GIFT_AMOUNT
import christmas.util.Constants.MINIMUM_BENEFIT_AMOUNT
import christmas.util.Constants.MINIMUM_GIFT_AMOUNT
import christmas.util.Constants.SANTA_BADGE_BENEFIT_AMOUNT
import christmas.util.Constants.SPECIAL_DAY_DISCOUNT_AMOUNT
import christmas.util.Constants.STAR_BADGE_BENEFIT_AMOUNT
import christmas.util.Constants.STEP_D_DAY_DISCOUNT_AMOUNT
import christmas.util.Constants.TREE_BADGE_BENEFIT_AMOUNT
import christmas.util.Constants.WEEK_DISCOUNT_AMOUNT
import christmas.util.OutputMessage

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
    private var giftBenefit = 0

    private var gift = OutputMessage.NO_BENEFIT.getMessage()
    private var badge = OutputMessage.NO_BENEFIT.getMessage()

    init {
        initializeTotalOrderAmountBeforeDiscount()
        if (totalOrderAmountBeforeDiscount >= MINIMUM_BENEFIT_AMOUNT) {
            initializeDiscounts()
            if (totalOrderAmountBeforeDiscount >= MINIMUM_GIFT_AMOUNT) {
                initializeGift()
            }
            initializeTotalBenefitAmount()
            initializeTotalOrderAmountAfterDiscount()
            initializeBadge()
        }
    }

    private fun initializeTotalOrderAmountBeforeDiscount() {
        totalOrderAmountBeforeDiscount = orders.sumOf { order -> order.getOrderAmount() }
    }

    private fun initializeTotalOrderAmountAfterDiscount() {
        totalOrderAmountAfterDiscount = totalOrderAmountBeforeDiscount - totalBenefitAmount + giftBenefit
    }

    private fun initializeDiscounts() {
        if (visitDay in EVENT_START_DAY..D_DAY_EVENT_END_DAY) {
            benefitDDayDiscount(visitDay)
        }
        if (weekendDays.contains(visitDay)) {
            val numberOfMain = getNumberOfMain()
            benefitWeekendDayDiscount(numberOfMain)
        } else {
            val numberOfDessert = getNumberOfDessert()
            benefitWeekDayDiscount(numberOfDessert)
        }
        if (specialDays.contains(visitDay)) {
            benefitSpecialDayDiscount()
        }
    }

    private fun initializeGift() {
        gift = OutputMessage.GIFT_CHAMPAGNE.getMessage()
        giftBenefit = GIFT_AMOUNT
    }

    private fun initializeBadge() {
        badge = when {
            totalBenefitAmount >= SANTA_BADGE_BENEFIT_AMOUNT -> OutputMessage.SANTA.getMessage()
            totalBenefitAmount >= TREE_BADGE_BENEFIT_AMOUNT -> OutputMessage.TREE.getMessage()
            totalBenefitAmount >= STAR_BADGE_BENEFIT_AMOUNT -> OutputMessage.STAR.getMessage()
            else -> OutputMessage.NO_BENEFIT.getMessage()
        }
    }

    private fun benefitDDayDiscount(visitDay: Int) {
        dDayDiscount = BASIC_D_DAY_DISCOUNT_AMOUNT + (visitDay - 1) * STEP_D_DAY_DISCOUNT_AMOUNT
    }

    private fun benefitWeekendDayDiscount(numberOfMain: Int) {
        weekendDayDiscount = numberOfMain * WEEK_DISCOUNT_AMOUNT
    }

    private fun getNumberOfMain(): Int {
        var numberOfMain = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == OutputMessage.MAIN.getMessage()) {
                numberOfMain += order.getOrderQuantity()
            }
        }
        return numberOfMain
    }

    private fun benefitWeekDayDiscount(numberOfDessert: Int) {
        weekDayDiscount = numberOfDessert * WEEK_DISCOUNT_AMOUNT
    }

    private fun getNumberOfDessert(): Int {
        var numberOfDessert = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == OutputMessage.DESSERT.getMessage()) {
                numberOfDessert += order.getOrderQuantity()
            }
        }
        return numberOfDessert
    }

    private fun benefitSpecialDayDiscount() {
        specialDayDiscount = SPECIAL_DAY_DISCOUNT_AMOUNT
    }

    private fun initializeTotalBenefitAmount() {
        totalBenefitAmount = dDayDiscount + weekendDayDiscount + weekDayDiscount + specialDayDiscount + giftBenefit
    }

    fun getTotalOrderAmountBeforeDiscount(): Int = totalOrderAmountBeforeDiscount

    fun getTotalOrderAmountAfterDiscount(): Int = totalOrderAmountAfterDiscount

    fun getTotalBenefitAmount(): Int = totalBenefitAmount

    fun getDDayDiscount(): Int = dDayDiscount

    fun getWeekendDayDiscount(): Int = weekendDayDiscount

    fun getWeekDayDiscount(): Int = weekDayDiscount

    fun getSpecialDayDiscount(): Int = specialDayDiscount

    fun getGiftBenefit(): Int = giftBenefit

    fun getGift(): String = gift

    fun getBadge(): String = badge
}