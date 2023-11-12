package christmas.model

import christmas.util.Constants.NO_BENEFIT_AMOUNT
import christmas.util.Constants.SANTA_BADGE_BENEFIT_AMOUNT
import christmas.util.Constants.STAR_BADGE_BENEFIT_AMOUNT
import christmas.util.Constants.TREE_BADGE_BENEFIT_AMOUNT

enum class Badge(private val rank: String, private val minimumBadgeBenefitAmount: Int) {
    SANTA("산타", SANTA_BADGE_BENEFIT_AMOUNT),
    TREE("트리", TREE_BADGE_BENEFIT_AMOUNT),
    STAR("별", STAR_BADGE_BENEFIT_AMOUNT),
    NO_BADGE("없음", NO_BENEFIT_AMOUNT);

    fun initializeBadge(totalBenefitAmount: Int): Badge {
        return values().first { badge -> totalBenefitAmount >= badge.minimumBadgeBenefitAmount }
    }

    fun getRank(): String = rank
}