package com.mcustodio.jokenpokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcustodio.jokenpokemon.BuildConfig
import com.mcustodio.jokenpokemon.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        text_about_version.text = BuildConfig.VERSION_NAME
    }
}
