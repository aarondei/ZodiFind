package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegistrationActivity : Activity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

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

            } else if (createUser.length() in 1..4){
                //  MIN USERNAME REQUIREMENT 5 CHARACTERS

                Toast.makeText(this, "Username must contain at least 5 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if( createPass.length() in 1..7){
                //  MIN PASSWORD REQUIREMENT 5 CHARACTERS

                Toast.makeText(this, "Password must contain at least 8 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
        } else if (createPass.text.toString() != confirmPass.text.toString()) {
                //  CONFIRM PASSWORD MISMATCH

                Toast.makeText(this, "Confirm password again", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (Regex("[&=<>+,]").containsMatchIn(createUser.text.toString()) ||
                createUser.text.toString().contains("..")) {
                //INVALID CHARACTERS IN USERNAME

                Toast.makeText(this, "Username contains invalid characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (!enterEmail.text.toString().contains("@")) {
                //  EMAIL VALIDATION

                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else {
                // SUCCESSFUL REGISTRATION

                Toast.makeText(this, "You have successfully created an account!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity:: class.java)
                intent.putExtra("username", createUser.text.toString())
                intent.putExtra("password", createPass.text.toString())

                startActivity(intent)
            }
        }
    }
}