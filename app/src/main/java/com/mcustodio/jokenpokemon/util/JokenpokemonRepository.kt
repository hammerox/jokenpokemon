package com.mcustodio.jokenpokemon.util

import android.util.Log
import com.mcustodio.jokenpokemon.data.Score
import com.mcustodio.jokenpokemon.data.ScoreRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object JokenpokemonRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gamestjd.herokuapp.com/jokenpokemon/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(JokenpokemonService::class.java)


    fun getScores(callback: Callback<List<Score>>) {
        service.getScores().enqueue(callback)
    }

    fun sendScore(score: ScoreRequestBody, onResponse: ((call: Call<Unit>, response: Response<Unit>) -> Unit)) {
        service.postScore(score).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("AAA", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                onResponse(call, response)
            }
        })
    }
}