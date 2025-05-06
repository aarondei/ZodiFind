package cit.edu.zodifind.data.tarot

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PalmModel(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val domain: Int,
    @StringRes val content: Int
)
