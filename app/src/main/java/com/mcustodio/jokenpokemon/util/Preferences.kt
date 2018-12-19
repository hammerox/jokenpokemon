package com.mcustodio.jokenpokemon.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mcustodio.jokenpokemon.data.User

class Preferences(context: Context) {

    val PREFS_FILENAME = "com.mcustodio.jokenpokemon"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    private val gson = Gson()


    private val LOGGED_IN_USER = "LOGGED_IN_USER "
    var loggedInUser: User?
        get() = gson.fromJson(prefs.getString(LOGGED_IN_USER , null), User::class.java)
        set(value) = prefs.edit().putString(LOGGED_IN_USER , gson.toJson(value)).apply()


    private val LAST_LOGGED_IN_USER = "LAST_LOGGED_IN_USER "
    var lastLoggedInUser: User?
        get() = gson.fromJson(prefs.getString(LAST_LOGGED_IN_USER , null), User::class.java)
        set(value) = prefs.edit().putString(LAST_LOGGED_IN_USER , gson.toJson(value)).apply()


    fun clearAll() {
        prefs.edit().clear().apply()
    }


}