package christmas.util

enum class ErrorMessage(private val message: String) {
    ;

    fun getMessage(): String = "[ERROR] $message"
}