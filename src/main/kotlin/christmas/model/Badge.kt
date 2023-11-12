package christmas.model

import christmas.util.NumericConstants.NO_BENEFIT_AMOUNT
import christmas.util.NumericConstants.SANTA_BADGE_BENEFIT_AMOUNT
import christmas.util.NumericConstants.STAR_BADGE_BENEFIT_AMOUNT
import christmas.util.NumericConstants.TREE_BADGE_BENEFIT_AMOUNT
import christmas.util.StringConstants.NO_BENEFIT
import christmas.util.StringConstants.SANTA
import christmas.util.StringConstants.STAR
import christmas.util.StringConstants.TREE


enum class Badge(private val rank: String, private val minimumBadgeBenefitAmount: Int) {
    SANTA_BADGE(SANTA, SANTA_BADGE_BENEFIT_AMOUNT),
    TREE_BADGE(TREE, TREE_BADGE_BENEFIT_AMOUNT),
    STAR_BADGE(STAR, STAR_BADGE_BENEFIT_AMOUNT),
    NO_BADGE(NO_BENEFIT, NO_BENEFIT_AMOUNT);

    fun initializeBadge(totalBenefitAmount: Int): Badge {
        return values().first { badge -> totalBenefitAmount >= badge.minimumBadgeBenefitAmount }
    }

    fun getRank(): String = rank
}