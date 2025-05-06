package cit.edu.zodifind.data.tarot

import cit.edu.zodifind.R
import javax.inject.Inject

class TarotCards @Inject constructor() {

    private val allCards = listOf(
//        major arcana
        TarotModel(R.drawable.fool_card, R.string.fool_title, R.string.fool_past, R.string.fool_present, R.string.fool_future),
        TarotModel(R.drawable.magician_card, R.string.magician_title, R.string.magician_past, R.string.magician_present, R.string.magician_future),
        TarotModel(R.drawable.highpriestess_card, R.string.priestess_title, R.string.high_priestess_past, R.string.high_priestess_present, R.string.high_priestess_future),
        TarotModel(R.drawable.empress_card, R.string.empress_title, R.string.empress_past, R.string.empress_present, R.string.empress_future),
        TarotModel(R.drawable.emperor_card, R.string.emperor_title, R.string.emperor_past, R.string.emperor_present, R.string.emperor_future),
        TarotModel(R.drawable.hierophant_card, R.string.hierophant_title, R.string.hierophant_past, R.string.hierophant_present, R.string.hierophant_future),
        TarotModel(R.drawable.lovers_card, R.string.lovers_title, R.string.lovers_past, R.string.lovers_present, R.string.lovers_future),
        TarotModel(R.drawable.chariot_card, R.string.chariot_title, R.string.chariot_past, R.string.chariot_present, R.string.chariot_future),
        TarotModel(R.drawable.strength_card, R.string.strength_title, R.string.strength_past, R.string.strength_present, R.string.strength_future),
        TarotModel(R.drawable.hermit_card, R.string.hermit_title, R.string.hermit_past, R.string.hermit_present, R.string.hermit_future),
        TarotModel(R.drawable.wheeloffortune_card, R.string.wheel_title, R.string.wheel_past, R.string.wheel_present, R.string.wheel_future),
        TarotModel(R.drawable.justice_card, R.string.justice_title, R.string.justice_past, R.string.justice_present, R.string.justice_future),
        TarotModel(R.drawable.hangedone_card, R.string.hangedman_title, R.string.hanged_man_past, R.string.hanged_man_present, R.string.hanged_man_future),
        TarotModel(R.drawable.death_card, R.string.death_title, R.string.death_past, R.string.death_present, R.string.death_future),
        TarotModel(R.drawable.temperance_card, R.string.temperance_title, R.string.temperance_past, R.string.temperance_present, R.string.temperance_future),
        TarotModel(R.drawable.devil_card, R.string.devil_title, R.string.devil_past, R.string.devil_present, R.string.devil_future),
        TarotModel(R.drawable.tower_card, R.string.tower_title, R.string.tower_past, R.string.tower_present, R.string.tower_future),
        TarotModel(R.drawable.star_card, R.string.star_title, R.string.star_past, R.string.star_present, R.string.star_future),
        TarotModel(R.drawable.moon_card, R.string.moon_title, R.string.moon_past, R.string.moon_present, R.string.moon_future),
        TarotModel(R.drawable.sun_card, R.string.sun_title, R.string.sun_past, R.string.sun_present, R.string.sun_future),
        TarotModel(R.drawable.judgement_card, R.string.judgement_title, R.string.judgement_past, R.string.judgement_present, R.string.judgement_future),
        TarotModel(R.drawable.world_card, R.string.world_title, R.string.world_past, R.string.world_present, R.string.world_future),

//        pentacle suite
        TarotModel(R.drawable.aceofpentacle_card, R.string.pentacles_ace_title, R.string.pentacles_ace_past, R.string.pentacles_ace_present, R.string.pentacles_ace_future),
        TarotModel(R.drawable.twoofpentacles_card, R.string.pentacles_two_title, R.string.pentacles_2_past, R.string.pentacles_2_present, R.string.pentacles_2_future),
        TarotModel(R.drawable.threeofpentacles_card, R.string.pentacles_three_title, R.string.pentacles_3_past, R.string.pentacles_3_present, R.string.pentacles_3_future),
        TarotModel(R.drawable.fourofpentacles_card, R.string.pentacles_four_title, R.string.pentacles_4_past, R.string.pentacles_4_present, R.string.pentacles_4_future),
        TarotModel(R.drawable.fiveofpentacles_card, R.string.pentacles_five_title, R.string.pentacles_5_past, R.string.pentacles_5_present, R.string.pentacles_5_future),
        TarotModel(R.drawable.sixofpentacles_card, R.string.pentacles_six_title, R.string.pentacles_6_past, R.string.pentacles_6_present, R.string.pentacles_6_future),
        TarotModel(R.drawable.sevenofpentacles_card, R.string.pentacles_seven_title, R.string.pentacles_7_past, R.string.pentacles_7_present, R.string.pentacles_7_future),
        TarotModel(R.drawable.eightofpentacles_card, R.string.pentacles_eight_title, R.string.pentacles_8_past, R.string.pentacles_8_present, R.string.pentacles_8_future),
        TarotModel(R.drawable.nineofpentacles_card, R.string.pentacles_nine_title, R.string.pentacles_9_past, R.string.pentacles_9_present, R.string.pentacles_9_future),
        TarotModel(R.drawable.tenofpentacles_card, R.string.pentacles_ten_title, R.string.pentacles_10_past, R.string.pentacles_10_present, R.string.pentacles_10_future),
        TarotModel(R.drawable.pageofpentacles_card, R.string.pentacles_page_title, R.string.pentacles_page_past, R.string.pentacles_page_present, R.string.pentacles_page_future),
        TarotModel(R.drawable.knightofpentacles_card, R.string.pentacles_knight_title, R.string.pentacles_knight_past, R.string.pentacles_knight_present, R.string.pentacles_knight_future),
        TarotModel(R.drawable.queenofpentacles_card, R.string.pentacles_queen_title, R.string.pentacles_queen_past, R.string.pentacles_queen_present, R.string.pentacles_queen_future),
        TarotModel(R.drawable.kingofpentacles_card, R.string.pentacles_king_title, R.string.pentacles_king_past, R.string.pentacles_king_present, R.string.pentacles_king_future),

//        sword suite
        TarotModel(R.drawable.aceofcups_card, R.string.cups_ace_title, R.string.cups_ace_past, R.string.cups_ace_present, R.string.cups_ace_future),
        TarotModel(R.drawable.twoofcups_card, R.string.cups_two_title, R.string.cups_2_past, R.string.cups_2_present, R.string.cups_2_future),
        TarotModel(R.drawable.threeofcups_card, R.string.cups_three_title, R.string.cups_3_past, R.string.cups_3_present, R.string.cups_3_future),
        TarotModel(R.drawable.fourofcups_card, R.string.cups_four_title, R.string.cups_4_past, R.string.cups_4_present, R.string.cups_4_future),
        TarotModel(R.drawable.fiveofcups_card, R.string.cups_five_title, R.string.cups_5_past, R.string.cups_5_present, R.string.cups_5_future),
        TarotModel(R.drawable.sixofcups_card, R.string.cups_six_title, R.string.cups_6_past, R.string.cups_6_present, R.string.cups_6_future),
        TarotModel(R.drawable.sevenofcups_card, R.string.cups_seven_title, R.string.cups_7_past, R.string.cups_7_present, R.string.cups_7_future),
        TarotModel(R.drawable.eightofcups_card, R.string.cups_eight_title, R.string.cups_8_past, R.string.cups_8_present, R.string.cups_8_future),
        TarotModel(R.drawable.nineofcups_card, R.string.cups_nine_title, R.string.cups_9_past, R.string.cups_9_present, R.string.cups_9_future),
        TarotModel(R.drawable.tenofcups_card, R.string.cups_ten_title, R.string.cups_10_past, R.string.cups_10_present, R.string.cups_10_future),
        TarotModel(R.drawable.pageofcups_card, R.string.cups_page_title, R.string.cups_page_past, R.string.cups_page_present, R.string.cups_page_future),
        TarotModel(R.drawable.knightofcups_card, R.string.cups_knight_title, R.string.cups_knight_past, R.string.cups_knight_present, R.string.cups_knight_future),
        TarotModel(R.drawable.queenofcups_card, R.string.cups_queen_title, R.string.cups_queen_past, R.string.cups_queen_present, R.string.cups_queen_future),
        TarotModel(R.drawable.kingofcups_card, R.string.cups_king_title, R.string.cups_king_past, R.string.cups_king_present, R.string.cups_king_future),

//        sword suite
        TarotModel(R.drawable.aceofswords_card, R.string.swords_ace_title, R.string.swords_ace_past, R.string.swords_ace_present, R.string.swords_ace_future),
        TarotModel(R.drawable.twoofswords_card, R.string.swords_two_title, R.string.swords_2_past, R.string.swords_2_present, R.string.swords_2_future),
        TarotModel(R.drawable.threeofswords_card, R.string.swords_three_title, R.string.swords_3_past, R.string.swords_3_present, R.string.swords_3_future),
        TarotModel(R.drawable.fourofswords_card, R.string.swords_four_title, R.string.swords_4_past, R.string.swords_4_present, R.string.swords_4_future),
        TarotModel(R.drawable.fiveofswords_card, R.string.swords_five_title, R.string.swords_5_past, R.string.swords_5_present, R.string.swords_5_future),
        TarotModel(R.drawable.sixofswords_card, R.string.swords_six_title, R.string.swords_6_past, R.string.swords_6_present, R.string.swords_6_future),
        TarotModel(R.drawable.sevenofswords_card, R.string.swords_seven_title, R.string.swords_7_past, R.string.swords_7_present, R.string.swords_7_future),
        TarotModel(R.drawable.eightofswords_card, R.string.swords_eight_title, R.string.swords_8_past, R.string.swords_8_present, R.string.swords_8_future),
        TarotModel(R.drawable.nineofswords_card, R.string.swords_nine_title, R.string.swords_9_past, R.string.swords_9_present, R.string.swords_9_future),
        TarotModel(R.drawable.tenofswords_card, R.string.swords_ten_title, R.string.swords_10_past, R.string.swords_10_present, R.string.swords_10_future),
        TarotModel(R.drawable.pageofswords_card, R.string.swords_page_title, R.string.swords_page_past, R.string.swords_page_present, R.string.swords_page_future),
        TarotModel(R.drawable.knightofswords_card, R.string.swords_knight_title, R.string.swords_knight_past, R.string.swords_knight_present, R.string.swords_knight_future),
        TarotModel(R.drawable.queenofswords_card, R.string.swords_queen_title, R.string.swords_queen_past, R.string.swords_queen_present, R.string.swords_queen_future),
        TarotModel(R.drawable.kingofswords_card, R.string.swords_king_title, R.string.swords_king_past, R.string.swords_king_present, R.string.swords_king_future),

//        wand suite
        TarotModel(R.drawable.aceofwands_card, R.string.wands_ace_title, R.string.wands_ace_past, R.string.wands_ace_present, R.string.wands_ace_future),
        TarotModel(R.drawable.twoofwands_card, R.string.wands_two_title, R.string.wands_2_past, R.string.wands_2_present, R.string.wands_2_future),
        TarotModel(R.drawable.threeofwands_card, R.string.wands_three_title, R.string.wands_3_past, R.string.wands_3_present, R.string.wands_3_future),
        TarotModel(R.drawable.fourofwands_card, R.string.wands_four_title, R.string.wands_4_past, R.string.wands_4_present, R.string.wands_4_future),
        TarotModel(R.drawable.fiveofwands_card, R.string.wands_five_title, R.string.wands_5_past, R.string.wands_5_present, R.string.wands_5_future),
        TarotModel(R.drawable.sixofwands_card, R.string.wands_six_title, R.string.wands_6_past, R.string.wands_6_present, R.string.wands_6_future),
        TarotModel(R.drawable.sevenofwands_card, R.string.wands_seven_title, R.string.wands_7_past, R.string.wands_7_present, R.string.wands_7_future),
        TarotModel(R.drawable.eightofwands_card, R.string.wands_eight_title, R.string.wands_8_past, R.string.wands_8_present, R.string.wands_8_future),
        TarotModel(R.drawable.nineofwands_card, R.string.wands_nine_title, R.string.wands_9_past, R.string.wands_9_present, R.string.wands_9_future),
        TarotModel(R.drawable.tenofwands_card, R.string.wands_ten_title, R.string.wands_10_past, R.string.wands_10_present, R.string.wands_10_future),
        TarotModel(R.drawable.pageofwands_card, R.string.wands_page_title, R.string.wands_page_past, R.string.wands_page_present, R.string.wands_page_future),
        TarotModel(R.drawable.knightofwands_card, R.string.wands_knight_title, R.string.wands_knight_past, R.string.wands_knight_present, R.string.wands_knight_future),
        TarotModel(R.drawable.queenofwands_card, R.string.wands_queen_title, R.string.wands_queen_past, R.string.wands_queen_present, R.string.wands_queen_future),
        TarotModel(R.drawable.kingofwands_card, R.string.wands_king_title, R.string.wands_king_past, R.string.wands_king_present, R.string.wands_king_future)
        )


    fun getLuckyList(): List<TarotModel> {
        return allCards
    }
}
