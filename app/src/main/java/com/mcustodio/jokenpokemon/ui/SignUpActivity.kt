package com.mcustodio.jokenpokemon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.data.User
import com.mcustodio.jokenpokemon.util.Preferences
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    private val genderValues = User.Gender.values().map { it.name }
    private val genderAdapter by lazy { ArrayAdapter(this, R.layout.component_spinner, genderValues) }
    private val preferences by lazy { Preferences(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_signup_gender.adapter = genderAdapter
        spinner_signup_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(adapter: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                edit_signup_gender.setText(genderValues[position])
            }
        }

        button_signup.setOnClickListener {
            if (inputsAreValid()) {
                val user = User(
                    edit_signup_email.text.toString(),
                    edit_signup_fullname.text.toString(),
                    edit_signup_nickname.text.toString(),
                    User.Gender.valueOf(edit_signup_gender.text.toString()),
                    edit_signup_password.text.toString()
                )

                preferences.loggedInUser = user
                preferences.lastLoggedInUser = user

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }


    private fun inputsAreValid(): Boolean {
        editlayout_signup_email.error = null
        editlayout_signup_fullname.error = null
        editlayout_signup_nickname.error = null
        editlayout_signup_gender.error = null
        editlayout_signup_password.error = null

        var isValid = true

        if (edit_signup_email.text.toString().isBlank()) {
            editlayout_signup_email.error = "Field is required"
            isValid = false
        }

        if (edit_signup_fullname.text.toString().isBlank()) {
            editlayout_signup_fullname.error = "Field is required"
            isValid = false
        }

        if (edit_signup_nickname.text.toString().isBlank()) {
            editlayout_signup_nickname.error = "Field is required"
            isValid = false
        }

        if (edit_signup_gender.text.toString().isBlank()) {
            editlayout_signup_gender.error = "Field is required"
            isValid = false
        }

        if (edit_signup_password.text.toString().isBlank()) {
            editlayout_signup_password.error = "Field is required"
            isValid = false
        }

        return isValid
    }
}
