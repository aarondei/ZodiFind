package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : Activity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val toRegister = findViewById<TextView>(R.id.toLogin)
        toRegister.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }

        val btnCreate =  findViewById<Button>(R.id.btnSignup)

        val createUser = findViewById<EditText>(R.id.etCreateUsername)
        val enterEmail = findViewById<EditText>(R.id.etCreateEmail)
        val createPass = findViewById<EditText>(R.id.etCreatePassword)

        btnCreate.setOnClickListener(){
            if(createUser.text.isNullOrEmpty() || createPass.text.isNullOrEmpty() || enterEmail.text.isNullOrEmpty()){
                Toast.makeText(this, "All fields must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            } else if(createUser.length() in 1..7|| createUser.length() in 1..7){
                Toast.makeText(this, "Username and password must contain 8 or more characters", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "You have successfully created an account!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, WesternHomeActivity:: class.java)
                intent.putExtra("username", createUser.text.toString())
                startActivity(intent)
            }
        }
    }
}