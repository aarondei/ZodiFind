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
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val edittext_username = findViewById<EditText>(R.id.edittext_username)
        val edittext_pass = findViewById<EditText>(R.id.edittext_pass)

        val btnSave =  findViewById<Button>(R.id.btnLogin)
        btnSave.setOnClickListener(){
            if(edittext_username.text.isNullOrEmpty() || edittext_pass.text.isNullOrEmpty()){
                Toast.makeText(this, "Username and Password must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if(edittext_username.text.contentEquals("admin", true) && edittext_pass.text.contentEquals("1234", true)){
                    Toast.makeText(this, "Congrats!", Toast.LENGTH_LONG).show()
                   val intent = Intent(this, WesternHomeActivity:: class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid!", Toast.LENGTH_LONG).show()
                }
            }

        val toRegister = findViewById<TextView>(R.id.toSignUp)
        toRegister.setOnClickListener(){
            val intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }
    }
}