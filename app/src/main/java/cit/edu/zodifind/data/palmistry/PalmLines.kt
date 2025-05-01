package cit.edu.zodifind.data.palmistry

import cit.edu.zodifind.R
import cit.edu.zodifind.luck.PalmModel

class PalmLines () {

    private val allLines = listOf(
        PalmModel(R.drawable.star, R.string.girdleofvenus_title , R.string.girdleofvenus_domain , R.string.girdleofvenus_content),
        PalmModel(R.drawable.star, R.string.marriageline_title , R.string.marriageline_domain , R.string.marriageline_content),
        PalmModel(R.drawable.star, R.string.lineofintuition_title , R.string.lineofintuition_domain , R.string.lineofintuition_content),
        PalmModel(R.drawable.star, R.string.lineofsun_title , R.string.lineofsun_domain , R.string.lineofsun_content),
        PalmModel(R.drawable.star, R.string.lineofdestiny_title , R.string.lineofdestiny_domain , R.string.lineofdestiny_content),
        PalmModel(R.drawable.star, R.string.lineofhealth_title , R.string.lineofhealth_domain , R.string.lineofhealth_content),
        PalmModel(R.drawable.star, R.string.lineofheart_title , R.string.lineofheart_domain , R.string.lineofheart_content),
        PalmModel(R.drawable.star, R.string.lineofhead_title , R.string.lineofhead_domain , R.string.lineofhead_content),
        PalmModel(R.drawable.star, R.string.lineofmars_title, R.string.lineofmars_domain , R.string.lineofmars_content),
    )
}