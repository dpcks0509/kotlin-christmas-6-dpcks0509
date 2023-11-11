package christmas.model

class Order(private val foodName: String, private val quantity: Int = 0) {
    fun getOrderFoodName(): String = foodName

    fun getOrderQuantity(): Int = quantity
}