package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.annotation.DrawableRes
import com.mashup.damgledamgle.R

enum class Reaction(
        @DrawableRes val selectedDrawableRes: Int,
        @DrawableRes val unselectedDrawableRes: Int,
        @DrawableRes val inactivateDrawableRes: Int,
) {
    LIKE(R.drawable.ic_like_yellow, R.drawable.ic_like, R.drawable.ic_like_inactive),
    ANGRY(R.drawable.ic_angry_yellow, R.drawable.ic_angry, R.drawable.ic_angry_inactive),
    AMAZING(R.drawable.ic_amazing_yellow, R.drawable.ic_amazing, R.drawable.ic_amazing_inactive),
    SAD(R.drawable.ic_sad_yellow, R.drawable.ic_sad, R.drawable.ic_sad_inactive),
    BEST(R.drawable.ic_best_yellow, R.drawable.ic_best, R.drawable.ic_best_inactive)
}
