package christmas.util

enum class ErrorMessage(private val message: String) {
    INVALID_VISIT_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ONLY_BEVERAGE_ORDER("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."),
    INVALID_TOTAL_ORDER_QUANTITY("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    fun getMessage(): String = "[ERROR] $message"
}