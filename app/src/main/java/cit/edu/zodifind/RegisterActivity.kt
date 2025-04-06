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

        val createUser = findViewById<EditText>(R.id.etCreateUsername)
        val enterEmail = findViewById<EditText>(R.id.etCreateEmail)
        val createPass = findViewById<EditText>(R.id.etCreatePassword)
        val confirmPass = findViewById<EditText>(R.id.etCreateConfirmPassword)

        val btnCreate =  findViewById<Button>(R.id.btnSignup)
        btnCreate.setOnClickListener(){

            if (createUser.text.isNullOrEmpty() || createPass.text.isNullOrEmpty() || enterEmail.text.isNullOrEmpty() || confirmPass.text.isNullOrEmpty()){
                // EMPTY FIELDS

                Toast.makeText(this, "All fields must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (createUser.length() in 1..7|| createPass.length() in 1..7){
                //  MIN REQUIREMENT 8 CHARACTERS

                Toast.makeText(this, "Username and password must contain at least 8 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (createPass.text != confirmPass.text) {
                //  CONFIRM PASSWORD MISMATCH

                Toast.makeText(this, "Confirm password again", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else {
                // SUCCESSFUL REGISTRATION

                Toast.makeText(this, "You have successfully created an account!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity:: class.java)
                intent.putExtra("username", createUser.text.toString())

                startActivity(intent)
            }
        }
    }
}