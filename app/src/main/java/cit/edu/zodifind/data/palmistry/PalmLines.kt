package cit.edu.zodifind.data.palmistry

import cit.edu.zodifind.R
import cit.edu.zodifind.luck.PalmModel

class PalmLines {
    companion object {
        fun getAllPalmLines(): List<PalmModel> {
            return listOf(
                PalmModel(R.drawable.girdleofvenus_domain, R.string.girdleofvenus_title, R.string.girdleofvenus_domain, R.string.girdleofvenus_content),
                PalmModel(R.drawable.marriageline_domain, R.string.marriageline_title, R.string.marriageline_domain, R.string.marriageline_content),
                PalmModel(R.drawable.lineofintuition_domain, R.string.lineofintuition_title, R.string.lineofintuition_domain, R.string.lineofintuition_content),
                PalmModel(R.drawable.lineofsun_domain, R.string.lineofsun_title, R.string.lineofsun_domain, R.string.lineofsun_content),
                PalmModel(R.drawable.lineofdestiny_domain, R.string.lineofdestiny_title, R.string.lineofdestiny_domain, R.string.lineofdestiny_content),
                PalmModel(R.drawable.lineofhealth_domain, R.string.lineofhealth_title, R.string.lineofhealth_domain, R.string.lineofhealth_content),
                PalmModel(R.drawable.lineofheartdomain, R.string.lineofheart_title, R.string.lineofheart_domain, R.string.lineofheart_content),
                PalmModel(R.drawable.lineofhead_domain, R.string.lineofhead_title, R.string.lineofhead_domain, R.string.lineofhead_content),
                PalmModel(R.drawable.lineofmars_domain, R.string.lineofmars_title, R.string.lineofmars_domain, R.string.lineofmars_content)
            )
        }
    }
}