package com.mcustodio.jokenpokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.data.Score
import com.mcustodio.jokenpokemon.util.JokenpokemonRepository
import kotlinx.android.synthetic.main.activity_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingActivity : AppCompatActivity() {

    private val adapter by lazy { RankingRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        recycler_ranking.layoutManager = LinearLayoutManager(this)
        recycler_ranking.adapter = adapter

        JokenpokemonRepository.getScores(object : Callback<List<Score>> {
            override fun onFailure(call: Call<List<Score>>, t: Throwable) {
                Log.e("AAA", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<List<Score>>, response: Response<List<Score>>) {
                if (response.isSuccessful) {
                    response.body()?.let { adapter.scoreList = it }
                }
            }
        })
    }
}
