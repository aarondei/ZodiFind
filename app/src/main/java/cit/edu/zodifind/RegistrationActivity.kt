package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import cit.edu.zodifind.data.CurrentUser
import cit.edu.zodifind.data.User

class RegistrationActivity : Activity() {

    private lateinit var newUser: User
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val etName = findViewById<EditText>(R.id.etName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val btnRegister =  findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener(){

            if (etName.text.isNullOrEmpty() || etUsername.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty() || etConfirmPassword.text.isNullOrEmpty()) {
                // EMPTY FIELDS

                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (etUsername.text.length in 1..4){
                //  MIN USERNAME REQUIREMENT 5 CHARACTERS

                Toast.makeText(this, "Username must contain at least 5 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (etPassword.text.length in 1..7){
                //  MIN PASSWORD REQUIREMENT 5 CHARACTERS

                Toast.makeText(this, "Password must contain at least 8 characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
                //  CONFIRM PASSWORD MISMATCH

                Toast.makeText(this, "Confirm password again", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else if (Regex("[&=<>+,]").containsMatchIn(etUsername.text.toString()) || etUsername.text.toString().contains("..")) {
                //INVALID CHARACTERS IN USERNAME

                Toast.makeText(this, "Username must not contain invalid characters", Toast.LENGTH_LONG).show()
                return@setOnClickListener;

            } else {
                // SUCCESSFUL REGISTRATION

                Toast.makeText(this, "Account successfully created", Toast.LENGTH_LONG).show()

                newUser = User(etName.text.toString(), etUsername.text.toString(), etPassword.text.toString())
                CurrentUser.user = newUser

                val intent = Intent(this, LoginActivity:: class.java)
                intent.putExtra("username", etUsername.text.toString())
                intent.putExtra("password", etPassword.text.toString())
                startActivity(intent)

                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        tvLogin.setOnClickListener(){
            startActivity(Intent(this, LoginActivity:: class.java))
        }
    }
}