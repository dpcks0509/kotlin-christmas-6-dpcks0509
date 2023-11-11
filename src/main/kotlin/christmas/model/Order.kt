package christmas.model

class Order(private val foodName: String, private val quantity: Int = 0) {
    private var orderAmount = 0

    init {
        orderAmount = calculateOrderAmount()
    }

    fun getOrderFoodName(): String = foodName

    fun getOrderQuantity(): Int = quantity

    fun getOrderAmount(): Int = orderAmount

    private fun calculateOrderAmount(): Int {
        val menu = Menu.values().find { menu -> menu.isFoodInMenu(getOrderFoodName()) }
        val foodPrice = menu?.findFoodPrice(getOrderFoodName())!!
        return foodPrice * quantity
    }

    override fun toString(): String {
        return "$foodName ${quantity}개"
    }
}