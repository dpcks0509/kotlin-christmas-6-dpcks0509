package christmas.model

class Order(private val foodName: String, private val quantity: Int = 0) {
    fun getOrderFoodName(): String = foodName

    fun getOrderQuantity(): Int = quantity

    override fun toString(): String {
        return "$foodName ${quantity}개"
    }
}