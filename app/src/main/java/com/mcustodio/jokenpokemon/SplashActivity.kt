package com.mcustodio.jokenpokemon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({
                // After the splash screen duration, route to the right activities
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },2000L
        )
    }

}
