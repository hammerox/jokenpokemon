package com.mcustodio.jokenpokemon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.util.Preferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val preferences by lazy { Preferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            val lastLoggedInUser = preferences.lastLoggedInUser
            val inputEmail = edit_login_email.text.toString()
            val inputPassword = edit_login_password.text.toString()

            if (inputEmail == lastLoggedInUser?.email && inputPassword == lastLoggedInUser.password) {
                preferences.loggedInUser = lastLoggedInUser
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid access", Toast.LENGTH_LONG).show()
            }
        }

        button_login_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
