package christmas.view

import christmas.util.OutputMessage

class OutputView {
    fun printErrorMessage(errorMessage: String) {
        println(errorMessage)
    }

    fun printVisitDayInstruction() {
        println(OutputMessage.VISIT_DAY_INSTRUCTION.getMessage())
    }

    fun printOrderMenusInstruction() {
        println(OutputMessage.ORDER_MENUS_INSTRUCTION.getMessage())
    }

    fun printBenefitPreviewInstruction() {
        println(OutputMessage.BENEFIT_PREVIEW_INSTRUCTION.getMessage())
    }
}