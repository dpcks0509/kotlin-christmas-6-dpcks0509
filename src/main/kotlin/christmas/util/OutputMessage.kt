package christmas.util

enum class OutputMessage(private val message: String) {
    VISIT_DAY_INSTRUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENUS_INSTRUCTION("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_PREVIEW_INSTUCTION("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERS_HEADER("\n<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_HEADER("\n<할인 전 총주문 금액>"),
    GIFT_MENU_HEADER("\n<증정 메뉴>"),
    BENEFIT_DETAILS_HEADER("\n<혜택 내역>"),
    D_DAY_DISCOUNT("크리스마스 디데이 할인: -%s원"),
    WEEKEND_DAY_DISCOUNT("주말 할인: -%s원"),
    WEEK_DAY_DISCOUNT("평일 할인: -%s원"),
    SPECIAL_DAY_DISCOUNT("특별 할인: -%s원"),
    GIFT_BENEFIT("증정 이벤트: -%s원"),
    NO_BENEFIT("없음");

    fun getMessage(): String = message
}