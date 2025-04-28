package cit.edu.zodifind.data


import cit.edu.zodifind.R
import cit.edu.zodifind.luck.LuckyModel
import javax.inject.Inject
import kotlin.random.Random

class RandomCardProvider @Inject constructor() {
    fun getLucky(): LuckyModel? {
        return when(Random.nextInt(0, 8)){
            0 -> LuckyModel(R.drawable.emperatriz_card, R.string.empress_title, R.string.empress_past, R.string.empress_present, R.string.empress_future)
            1 -> LuckyModel(R.drawable.juicio_card, R.string.judgement_title, R.string.judgement_past, R.string.judgement_present, R.string.judgement_future)
            2 -> LuckyModel(R.drawable.fuerza_card, R.string.empress_title, R.string.strength_past, R.string.strength_present, R.string.strength_future)
            3 -> LuckyModel(R.drawable.amantes_card, R.string.lovers_title, R.string.lovers_past, R.string.lovers_present, R.string.lovers_future)
            4 -> LuckyModel(R.drawable.loco_card, R.string.fool_title, R.string.fool_past, R.string.fool_present, R.string.fool_future)
            5 -> LuckyModel(R.drawable.mago_card, R.string.magician_title, R.string.magician_past, R.string.magician_present, R.string.magician_future)
            6 -> LuckyModel(R.drawable.muerte_card, R.string.death_title, R.string.death_past, R.string.death_present, R.string.death_future)
            7 -> LuckyModel(R.drawable.sol_card, R.string.sun_title, R.string.sun_past, R.string.sun_present, R.string.sun_future)
            8 -> LuckyModel(R.drawable.sacerdotisa_card, R.string.chariot_title, R.string.chariot_past, R.string.chariot_present, R.string.chariot_future)

            else -> null
        }
    }

    fun getLuckyList(): List<LuckyModel> { // TODO store in object class
        return listOf(
            LuckyModel(
                R.drawable.emperatriz_card,
                R.string.empress_title,
                R.string.empress_past,
                R.string.empress_present,
                R.string.empress_future
            ),
            LuckyModel(
                R.drawable.juicio_card,
                R.string.judgement_title,
                R.string.judgement_past,
                R.string.judgement_present,
                R.string.judgement_future
            ),
            LuckyModel(
                R.drawable.fuerza_card,
                R.string.empress_title,
                R.string.strength_past,
                R.string.strength_present,
                R.string.strength_future
            ),
            LuckyModel(
                R.drawable.amantes_card,
                R.string.lovers_title,
                R.string.lovers_past,
                R.string.lovers_present,
                R.string.lovers_future
            ),
            LuckyModel(
                R.drawable.loco_card,
                R.string.fool_title,
                R.string.fool_past,
                R.string.fool_present,
                R.string.fool_future
            ),
            LuckyModel(
                R.drawable.mago_card,
                R.string.magician_title,
                R.string.magician_past,
                R.string.magician_present,
                R.string.magician_future
            ),
            LuckyModel(
                R.drawable.muerte_card,
                R.string.death_title,
                R.string.death_past,
                R.string.death_present,
                R.string.death_future
            ),
            LuckyModel(
                R.drawable.sol_card,
                R.string.sun_title,
                R.string.sun_past,
                R.string.sun_present,
                R.string.sun_future
            ),
            LuckyModel(
                R.drawable.sacerdotisa_card,
                R.string.chariot_title,
                R.string.chariot_past,
                R.string.chariot_present,
                R.string.chariot_future
            )
        )
    }
}