package cit.edu.zodifind.app

import android.app.Application
import cit.edu.zodifind.data.CapturedZodiacTempObject
import cit.edu.zodifind.data.User
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZodiFindApplication : Application() {

    var currentUser: User? = null
    val registeredUsers = mutableListOf<User>()

    fun logout() {
        currentUser = null
        CapturedZodiacTempObject.capturedSign = null
    }

}