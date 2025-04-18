package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : Activity() {

    private var createdUser: String? = null
    private var createdPassword: String? = null

    @Suppress("DEPRECATION")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val etUsername = findViewById<EditText>(R.id.etName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        //created account
        intent?.let {
            it.getStringExtra("username")?.let{username ->
                createdUser = username;
            }

            it.getStringExtra("password")?.let{password ->
                createdPassword = password;
            }
        }

        val btnSave = findViewById<Button>(R.id.btnLogin)
        btnSave.setOnClickListener(){
            if (etUsername.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            } else if (etUsername.text.contentEquals("admin", true) && etPassword.text.contentEquals("1234", true)) {
                //ADMIN CREDENTIALS

                Toast.makeText(this, "Developer Mode", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity:: class.java))

            } else if(etUsername.text.toString() == createdUser && etPassword.text.toString() == createdPassword){
                //SUCCESS

                Toast.makeText(this, "Welcome, $createdUser !", Toast.LENGTH_LONG).show()

                val intent = Intent(this, HomeActivity:: class.java)
                intent.putExtra("username", etUsername.text.toString())
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            } else {
                //ERROR

                Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener(){
            startActivity(Intent(this, RegistrationActivity:: class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}