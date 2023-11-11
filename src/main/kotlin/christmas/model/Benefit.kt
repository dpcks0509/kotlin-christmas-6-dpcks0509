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

class Benefit(private val visitDay: Int, private val orders: List<Order>) {
    private val weekendDays = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    private val specialDays = listOf(3, 10, 17, 24, 25, 31)

    private var totalOrderAmount = 0
    private var totalBenefitAmount = 0

    private var dDayDiscount = 0
    private var weekendDayDiscount = 0
    private var weekDayDiscount = 0
    private var specialDayDiscount = 0
    private var giftBenefit = 0

    private var gift = "없음"
    private var badge = "없음"

    init {
        totalOrderAmount = initializeTotalOrderAmount()
        if (totalOrderAmount >= MINIMUM_BENEFIT_AMOUNT) {
            initializeTotalBenefitAmount()
            if (totalOrderAmount >= MINIMUM_GIFT_AMOUNT) {
                initializeGift()
            }
            initializeBadge()
        }
    }

    private fun initializeTotalOrderAmount(): Int {
        return orders.sumOf { order -> order.getOrderAmount() }
    }

    private fun initializeTotalBenefitAmount() {
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
        totalBenefitAmount = dDayDiscount + weekendDayDiscount + weekDayDiscount + specialDayDiscount + giftBenefit
    }

    private fun initializeGift() {
        gift = "샴페인 1개"
        giftBenefit = GIFT_AMOUNT
    }

    private fun initializeBadge() {
        badge = when {
            totalBenefitAmount >= SANTA_BADGE_BENEFIT_AMOUNT -> "산타"
            totalBenefitAmount >= TREE_BADGE_BENEFIT_AMOUNT -> "트리"
            totalBenefitAmount >= STAR_BADGE_BENEFIT_AMOUNT -> "별"
            else -> "없음"
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
            if (menu?.findCategory(order.getOrderFoodName()) == "메인") {
                numberOfMain += 1
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
            if (menu?.findCategory(order.getOrderFoodName()) == "디저트") {
                numberOfDessert += 1
            }
        }
        return numberOfDessert
    }

    private fun benefitSpecialDayDiscount() {
        specialDayDiscount = SPECIAL_DAY_DISCOUNT_AMOUNT
    }

    fun getTotalBenefitAmount(): Int = totalBenefitAmount

    fun getDDayDiscount(): Int = dDayDiscount

    fun getWeekendDayDiscount(): Int = weekendDayDiscount

    fun getWeekDayDiscount(): Int = weekDayDiscount

    fun getSpecialDayDiscount(): Int = specialDayDiscount

    fun getGiftBenefit(): Int = giftBenefit

    fun getGift(): String = gift

    fun getBadge(): String = badge
}