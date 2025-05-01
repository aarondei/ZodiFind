package cit.edu.zodifind.luck
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TarotModel (
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val pastMeaning: Int,

    @StringRes val presentMeaning: Int,
    @StringRes val futureMeaning: Int

)