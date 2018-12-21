package com.mcustodio.jokenpokemon.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.util.Preferences

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({
                // After the splash screen duration, route to the right activities
                val loggedInUser = Preferences(this).lastLoggedInUser
                val intent = when {
                    loggedInUser != null -> Intent(this, MainActivity::class.java)
                    else -> Intent(this, LoginActivity::class.java)
                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            },2000L
        )
    }

}
