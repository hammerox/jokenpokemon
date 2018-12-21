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

    private var score = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        image_game_grass.setOnClickListener { chooseAndShowResult(Element.Grass) }
        image_game_fire.setOnClickListener { chooseAndShowResult(Element.Fire) }
        image_game_water.setOnClickListener { chooseAndShowResult(Element.Water) }

        startNewGame()
    }

    override fun onStart() {
        super.onStart()
        startNewGame()
    }

    private fun chooseAndShowResult(userChoice: Element) {
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

    private fun startNewGame() {
        text_game_result.text = "Escolha sua jogada!"
        linear_game_options.visibility = View.VISIBLE
        image_game_yourchoice.setImageResource(android.R.color.transparent)
        image_game_enemychoice.setImageResource(android.R.color.transparent)
        score = 0L
    }

    private fun showGameOverScreen() {
        Handler().postDelayed({
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
        },1000L)
    }
}
