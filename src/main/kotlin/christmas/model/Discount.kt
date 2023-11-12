package christmas.model

import christmas.util.NumericConstants
import christmas.util.StringConstants

class Discount(private val visitDay: Int, private val orders: List<Order>) {
    private val weekendDays = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    private val specialDays = listOf(3, 10, 17, 24, 25, 31)

    private var dDayDiscount = 0
    private var weekendDayDiscount = 0
    private var weekDayDiscount = 0
    private var specialDayDiscount = 0

    fun initializeDiscounts() {
        if (visitDay in NumericConstants.EVENT_START_DAY..NumericConstants.D_DAY_EVENT_END_DAY) {
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

    private fun getNumberOfMain(): Int {
        var numberOfMain = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == StringConstants.MAIN) {
                numberOfMain += order.getOrderQuantity()
            }
        }
        return numberOfMain
    }

    private fun benefitWeekDayDiscount(numberOfDessert: Int): Int {
        return numberOfDessert * NumericConstants.WEEK_DISCOUNT_AMOUNT
    }

    private fun getNumberOfDessert(): Int {
        var numberOfDessert = 0
        orders.forEach { order ->
            val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
            if (menu?.findCategory(order.getOrderFoodName()) == StringConstants.DESSERT) {
                numberOfDessert += order.getOrderQuantity()
            }
        }
        return numberOfDessert
    }

    private fun benefitSpecialDayDiscount(): Int {
        return NumericConstants.SPECIAL_DAY_DISCOUNT_AMOUNT
    }

    private fun benefitDDayDiscount(visitDay: Int): Int {
        return NumericConstants.BASIC_D_DAY_DISCOUNT_AMOUNT + (visitDay - 1) * NumericConstants.STEP_D_DAY_DISCOUNT_AMOUNT
    }

    private fun benefitWeekendDayDiscount(numberOfMain: Int): Int {
        return numberOfMain * NumericConstants.WEEK_DISCOUNT_AMOUNT
    }

    fun getDDayDiscount(): Int = dDayDiscount

    fun getWeekendDayDiscount(): Int = weekendDayDiscount

    fun getWeekDayDiscount(): Int = weekDayDiscount

    fun getSpecialDayDiscount(): Int = specialDayDiscount
}