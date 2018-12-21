package com.mcustodio.jokenpokemon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.mcustodio.jokenpokemon.R
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random



class GameActivity : AppCompatActivity() {

    enum class Element(val imageRes: Int) {
        Grass(R.drawable.bulbasaur),
        Fire(R.drawable.charmander),
        Water(R.drawable.squirtle);

        fun winsAgainst(): Element = when(this) {
            Grass -> Water
            Fire -> Grass
            Water -> Fire
        }
    }

    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        image_game_grass.setOnClickListener { startGame(Element.Grass) }
        image_game_fire.setOnClickListener { startGame(Element.Fire) }
        image_game_water.setOnClickListener { startGame(Element.Water) }
    }

    private fun startGame(userChoice: Element) {
        image_game_yourchoice.setImageDrawable(resources.getDrawable(userChoice.imageRes, applicationContext.theme))

        val randomIndex = Random.nextInt(0,3)
        val enemyChoice = Element.values().get(randomIndex)
        image_game_enemychoice.setImageDrawable(resources.getDrawable(enemyChoice.imageRes, applicationContext.theme))

        when {
            userChoice.winsAgainst() == enemyChoice -> {
                score += 2
                text_game_result.text = "VITÓRIA: +2 insignias"
            }
            userChoice == enemyChoice -> {
                score += 1
                text_game_result.text = "EMPATE: +1 insignia"
            }
            else -> {
                linear_game_options.visibility = View.INVISIBLE
                text_game_result.text = "GAME OVER"
                showGameOverScreen()
            }
        }
    }

    private fun showGameOverScreen() {
        Handler().postDelayed({
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
        },1000L)
    }
}
