package com.mcustodio.jokenpokemon.util

import com.mcustodio.jokenpokemon.data.ScoreRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface JokenpokemonService {

    @POST("pontuacao")
    fun postScore(@Body score: ScoreRequestBody): Call<Unit>


}