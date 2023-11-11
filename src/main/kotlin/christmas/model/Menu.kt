package christmas.model

enum class Menu(private val category: String, private val foods: List<Food>) {
    APPETIZER("애피타이저", listOf(Food("양송이수프", 6000), Food("타파스", 5500), Food("시저샐러드", 8000))),
    MAIN("메인", listOf(Food("티본스테이크", 55000), Food("바비큐립", 54000), Food("해산물파스타", 35000), Food("크리스마스파스타", 25000))),
    DESSERT("디저트", listOf(Food("초코케이크", 15000), Food("아이스크림", 5000))),
    BEVERAGE("음료", listOf(Food("제로콜라", 3000), Food("레드와인", 60000), Food("샴페인", 25000)));

    fun findCategory(foodName: String): String {
        return values().find { menu -> menu.isFoodInMenu(foodName) }?.category!!
    }

    fun findFoodPrice(foodName: String): Int {
        return foods.find { food -> food.getFoodName() == foodName }?.getFoodPrice()!!
    }

    fun isFoodInMenu(foodName: String): Boolean {
        return foods.any { food -> food.getFoodName() == foodName }
    }
}