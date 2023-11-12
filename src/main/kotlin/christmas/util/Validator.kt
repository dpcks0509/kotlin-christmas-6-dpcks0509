package christmas.util

import christmas.model.Menu
import christmas.model.Order
import christmas.util.NumericConstants.EVENT_END_DAY
import christmas.util.NumericConstants.EVENT_START_DAY

object Validator {
    fun validateVisitDay(visitDay: String): Int {
        val validVisitDay = visitDay.toIntOrNull()
        validateVisitDayFormat(validVisitDay)
        validateVisitDayRange(validVisitDay!!)
        return validVisitDay
    }

    private fun validateVisitDayFormat(visitDay: Int?) =
        requireNotNull(visitDay) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }

    private fun validateVisitDayRange(visitDay: Int) =
        require(visitDay in EVENT_START_DAY..EVENT_END_DAY) { ErrorMessage.INVALID_VISIT_DAY.getMessage() }

    fun validateOrders(orders: String): List<Order> {
        val validOrders = orders.split(",").map { order -> validateOrder(order) }
        validateOrderDuplicate(validOrders)
        validateOrderCategory(validOrders)
        validateTotalOrderQuantityRange(validOrders)
        return validOrders
    }

    private fun validateOrder(order: String): Order {
        validateOrderFormat(order)
        val validOrder = order.split("-")
        val validFoodName = validateOrderFoodName(validOrder[0])
        val validQuantity = validateOrderQuantity(validOrder[1])
        return Order(validFoodName, validQuantity)
    }

    private fun validateOrderFormat(order: String) =
        require(order.contains("-")) { ErrorMessage.INVALID_ORDER.getMessage() }

    private fun validateOrderFoodName(foodName: String): String {
        validateOrderFoodNameExists(foodName)
        return foodName
    }

    private fun validateOrderFoodNameExists(foodName: String) =
        require(Menu.values().any { menu -> menu.isFoodInMenu(foodName) }) { ErrorMessage.INVALID_ORDER.getMessage() }

    private fun validateOrderQuantity(quantity: String): Int {
        val validQuantity = quantity.toIntOrNull()
        validateOrderQuantityFormat(validQuantity)
        validateOrderQuantityRange(validQuantity!!)
        return validQuantity
    }

    private fun validateOrderQuantityFormat(quantity: Int?) =
        requireNotNull(quantity) { ErrorMessage.INVALID_ORDER.getMessage() }

    private fun validateOrderQuantityRange(quantity: Int) =
        require(quantity >= 1) { ErrorMessage.INVALID_ORDER.getMessage() }

    private fun validateOrderDuplicate(orders: List<Order>) {
        val orderFoodNames = orders.map { order ->
            order.getOrderFoodName()
        }
        require(orderFoodNames.size == orderFoodNames.toSet().size) { ErrorMessage.INVALID_ORDER.getMessage() }
    }

    private fun validateOrderCategory(orders: List<Order>) {
        val hasNonBeverageOrder = orders.any { order ->
            Menu.values().any { menu ->
                menu != Menu.BEVERAGE && menu.isFoodInMenu(order.getOrderFoodName())
            }
        }
        require(hasNonBeverageOrder) {
            ErrorMessage.INVALID_ONLY_BEVERAGE_ORDER.getMessage()
        }
    }

    private fun validateTotalOrderQuantityRange(orders: List<Order>) {
        val totalQuantity = orders.sumOf { order -> order.getOrderQuantity() }
        require(totalQuantity <= 20) { ErrorMessage.INVALID_TOTAL_ORDER_QUANTITY.getMessage() }
    }
}