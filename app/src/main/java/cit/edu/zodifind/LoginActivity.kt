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
    @Suppress("DEPRECATION")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val edittext_username = findViewById<EditText>(R.id.etUsername)
        val edittext_pass = findViewById<EditText>(R.id.etPassword)

        //created account
        var user:String? = null
        var pass:String? = null

        intent?.let {
            it.getStringExtra("username")?.let{username ->
                user  = username;
            }

            it.getStringExtra("password")?.let{password ->
                pass  = password;
            }
        }

        val btnSave =  findViewById<Button>(R.id.btnLogin)
        btnSave.setOnClickListener(){
            if(edittext_username.text.isNullOrEmpty() || edittext_pass.text.isNullOrEmpty()){
                Toast.makeText(this, "Username and Password must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if(edittext_username.text.contentEquals("admin", true) && edittext_pass.text.contentEquals("1234", true)){
                //ADMIN CREDENTIALS

                    Toast.makeText(this, "Developer Mode", Toast.LENGTH_LONG).show()
                   val intent = Intent(this, HomeActivity:: class.java)
                    startActivity(intent)
                } else if(edittext_username.text.toString() == user && edittext_pass.text.toString() == pass){
                    //USER VALIDATIONS

                Toast.makeText(this, "Welcome, $user !", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity:: class.java)
                intent.putExtra("username", edittext_username.text.toString())
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            } else {
                //ERROR

                    Toast.makeText(this, "Invalid!", Toast.LENGTH_LONG).show()
                }
            }

        val toRegister = findViewById<TextView>(R.id.tvSignUp)
        toRegister.setOnClickListener(){
            val intent = Intent(this, RegistrationActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}