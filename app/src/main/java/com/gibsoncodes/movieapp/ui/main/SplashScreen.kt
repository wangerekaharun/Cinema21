package com.gibsoncodes.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gibsoncodes.movieapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val job = CoroutineScope(Dispatchers.Main)
        job.launch {
            delay(4000L)
            startActivity(Intent(this@SplashScreen, MainScreen::class.java))
            finish()
        }
    }
}