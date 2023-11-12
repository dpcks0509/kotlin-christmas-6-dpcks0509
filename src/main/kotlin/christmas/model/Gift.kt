package christmas.model

import christmas.util.NumericConstants
import christmas.util.StringConstants

class Gift(
    private var champagne: String,
    private var benefitAmount: Int
) {
    fun initializeGift(): Gift {
        return Gift(StringConstants.GIFT_CHAMPAGNE, NumericConstants.GIFT_AMOUNT)
    }

    fun getChampagne(): String = champagne

    fun getBenefitAmount(): Int = benefitAmount
}