package christmas.util

enum class OutputMessage(private val message: String) {
    VISIT_DAY_INSTRUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENUS_INSTRUCTION("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_PREVIEW_HEADER("12월 n(방문 날짜)일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERS_HEADER("\n<주문 메뉴>");

    fun getMessage(): String = message
}