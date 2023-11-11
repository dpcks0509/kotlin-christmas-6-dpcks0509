package christmas.model

class Food(private val name: String, private val price: Int) {
    fun getFoodName(): String = name

    fun getFoodPrice(): Int = price
}