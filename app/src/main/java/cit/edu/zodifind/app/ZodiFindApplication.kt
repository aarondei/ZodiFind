package cit.edu.zodifind.app

import android.app.Application
import cit.edu.zodifind.data.User

class ZodiFindApplication : Application() {

    var currentUser: User? = null
    val registeredUsers = mutableListOf<User>()

}