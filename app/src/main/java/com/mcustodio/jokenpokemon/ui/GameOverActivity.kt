package com.mcustodio.jokenpokemon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.data.ScoreRequestBody
import com.mcustodio.jokenpokemon.util.JokenpokemonRepository
import com.mcustodio.jokenpokemon.util.Preferences
import kotlinx.android.synthetic.main.activity_gameover.*

class GameOverActivity : AppCompatActivity() {

    private val scoreValue: Long by lazy { intent.getLongExtra("score", 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameover)

        text_gameover_score.text = "$scoreValue insignias"
        edit_gameover_nickname.setText(Preferences(this).loggedInUser?.nickname)

        button_gameover_playagain.setOnClickListener {
            val nickname = edit_gameover_nickname.text.toString()
            val score = ScoreRequestBody(nickname, scoreValue)
            JokenpokemonRepository.sendScore(score) { _, response ->
                if (response.isSuccessful) {
                    finish()
                } else {
                    Toast.makeText(this, response.message(), Toast.LENGTH_LONG).show()
                }
            }
        }

        button_gameover_backtomenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
