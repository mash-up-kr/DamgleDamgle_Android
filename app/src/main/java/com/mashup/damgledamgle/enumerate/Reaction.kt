package com.mashup.damgledamgle.enumerate

import androidx.annotation.DrawableRes
import com.mashup.damgledamgle.R

enum class Reaction(
    @DrawableRes val selectedDrawableRes: Int,
    @DrawableRes val unselectedDrawableRes: Int,
    @DrawableRes val inactivateDrawableRes: Int,
    @DrawableRes val soloBackgroundDrawableRes: Int,
    @DrawableRes val mainImageDrawableRes: Int,
) {
    LIKE(
        R.drawable.ic_like_yellow,
        R.drawable.ic_like,
        R.drawable.ic_like_inactive,
        R.drawable.ic_like_solo_background,
        R.drawable.img_like
    ),
    ANGRY(
        R.drawable.ic_angry_yellow,
        R.drawable.ic_angry,
        R.drawable.ic_angry_inactive,
        R.drawable.ic_angry_solo_background,
        R.drawable.img_angry
    ),
    AMAZING(
        R.drawable.ic_amazing_yellow,
        R.drawable.ic_amazing,
        R.drawable.ic_amazing_inactive,
        R.drawable.ic_amazing_solo_background,
        R.drawable.img_amazing
    ),
    SAD(
        R.drawable.ic_sad_yellow,
        R.drawable.ic_sad,
        R.drawable.ic_sad_inactive,
        R.drawable.ic_sad_solo_background,
        R.drawable.img_sad
    ),
    BEST(
        R.drawable.ic_best_yellow,
        R.drawable.ic_best,
        R.drawable.ic_best_inactive,
        R.drawable.ic_best_solo_background,
        R.drawable.img_best
    )
}

fun String.toReaction(): Reaction? {
    return when (this) {
        "angry" -> Reaction.ANGRY
        "sad" -> Reaction.SAD
        "amazing" -> Reaction.AMAZING
        "best" -> Reaction.BEST
        "like" -> Reaction.LIKE
        else -> null
    }
}

fun Reaction.toEnglish(): String {
    return when (this) {
        Reaction.ANGRY -> "angry"
        Reaction.SAD -> "sad"
        Reaction.AMAZING -> "amazing"
        Reaction.BEST -> "best"
        Reaction.LIKE -> "like"
    }
}

fun Reaction.toKorean(): String {
    return when (this) {
        Reaction.SAD -> "슬픔"
        Reaction.BEST -> "최고"
        Reaction.AMAZING -> "놀라움"
        Reaction.ANGRY -> "화남"
        Reaction.LIKE -> "좋아요"
    }
}
