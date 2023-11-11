package christmas.model

class Order(private val foodName: String, private val quantity: Int = 0) {
    fun getOrderFoodName(): String = foodName

    fun getOrderQuantity(): Int = quantity

    fun calculateOrderAmount(order: Order): Int {
        val menu = Menu.values().find { menu -> menu.isFoodInMenu(order.getOrderFoodName()) }
        val foodPrice = menu?.findFoodPrice(order.getOrderFoodName())!!
        return foodPrice * quantity
    }

    override fun toString(): String {
        return "$foodName ${quantity}개"
    }
}