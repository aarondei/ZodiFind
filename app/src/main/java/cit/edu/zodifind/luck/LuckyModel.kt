package cit.edu.zodifind.luck
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LuckyModel (
    @DrawableRes val image: Int,
    @StringRes val text: Int,
    @StringRes val description: Int
)