package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.data.User

class LoginActivity : BaseActivity() {

    @Suppress("DEPRECATION")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val app = application as ZodiFindApplication

        val etUsername = findViewById<EditText>(R.id.etName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        // fill from current user from registration
        etUsername.setText(app.currentUser?.username ?: "")
        etPassword.setText(app.currentUser?.password ?: "")

        val btnSave = findViewById<Button>(R.id.btnLogin)
        btnSave.setOnClickListener(){

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                // EMPTY FIELDS

                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            } else if (username.contentEquals("admin", true) && password.contentEquals("1234", true)) {
                //ADMIN CREDENTIALS

                Toast.makeText(this, "Developer Mode", Toast.LENGTH_LONG).show()

                app.currentUser = User("Admin", "admin", "1234") // TODO DELETE THIS AFTER
                startActivity(Intent(this, VerificationFirstActivity:: class.java))
            } else {
                // if not empty, check if already registered

                val existingUser = app.registeredUsers.find { it.username == username }

                if (existingUser != null) { // user exists

                    if (existingUser.password == password) { // matched password

                        app.currentUser = existingUser

                        if (app.currentUser?.birthdate != null) { // user has set birthdate already

                            Toast.makeText(this, "Welcome back, ${app.currentUser?.name}", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, HomeActivity:: class.java))
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        }
                        else { // user has not yet set birthdate

                            Toast.makeText(this, "Welcome, ${app.currentUser?.name}", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, VerificationFirstActivity:: class.java))
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        }

                    }
                    else { // wrong password
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                } else { // user does not exist
                    //ERROR

                    Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

        }

        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener(){
            startActivity(Intent(this, RegistrationActivity:: class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}