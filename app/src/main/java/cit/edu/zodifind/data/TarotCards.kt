package cit.edu.zodifind.data

import cit.edu.zodifind.R
import cit.edu.zodifind.luck.LuckyModel
import javax.inject.Inject

class TarotCards @Inject constructor() {

    private val allCards = listOf(
        LuckyModel(R.drawable.sample_tarot, R.string.empress_title, R.string.empress_past, R.string.empress_present, R.string.empress_future),
        LuckyModel(R.drawable.sample_tarot, R.string.judgement_title, R.string.judgement_past, R.string.judgement_present, R.string.judgement_future),
        LuckyModel(R.drawable.sample_tarot, R.string.strength_title, R.string.strength_past, R.string.strength_present, R.string.strength_future),
        LuckyModel(R.drawable.sample_tarot, R.string.lovers_title, R.string.lovers_past, R.string.lovers_present, R.string.lovers_future),
        LuckyModel(R.drawable.sample_tarot, R.string.fool_title, R.string.fool_past, R.string.fool_present, R.string.fool_future),
        LuckyModel(R.drawable.sample_tarot, R.string.magician_title, R.string.magician_past, R.string.magician_present, R.string.magician_future),
        LuckyModel(R.drawable.sample_tarot, R.string.death_title, R.string.death_past, R.string.death_present, R.string.death_future),
        LuckyModel(R.drawable.sample_tarot, R.string.sun_title, R.string.sun_past, R.string.sun_present, R.string.sun_future),
        LuckyModel(R.drawable.sample_tarot, R.string.chariot_title, R.string.chariot_past, R.string.chariot_present, R.string.chariot_future)
    )


    fun getLuckyList(): List<LuckyModel> {
        return allCards
    }
}
