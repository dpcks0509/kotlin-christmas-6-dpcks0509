package christmas.model

import christmas.util.NumericConstants.GIFT_AMOUNT
import christmas.util.NumericConstants.MINIMUM_GIFT_AMOUNT
import christmas.util.NumericConstants.NO_BENEFIT_AMOUNT
import christmas.util.StringConstants.GIFT_CHAMPAGNE
import christmas.util.StringConstants.NO_BENEFIT

class Gift(
    private var champagne: String,
    private var benefitAmount: Int
) {
    fun initializeGift(totalOrderAmountBeforeDiscount: Int): Gift {
        if (totalOrderAmountBeforeDiscount >= MINIMUM_GIFT_AMOUNT) {
            return Gift(GIFT_CHAMPAGNE, GIFT_AMOUNT)
        }
        return Gift(NO_BENEFIT, NO_BENEFIT_AMOUNT)
    }

    fun getChampagne(): String = champagne

    fun getBenefitAmount(): Int = benefitAmount
}