package christmas.util

enum class OutputMessage(private val message: String) {
    VISIT_DAY_INSTRUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

    fun getMessage(): String = message
}