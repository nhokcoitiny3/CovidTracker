package com.tiny.covidtracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.tiny.covidtracker.ui.splash.SplashScreen
import com.tiny.covidtracker.ui.theme.CovidTrackerTheme

@SuppressLint("CustomSplashScreen")
@ExperimentalAnimationApi
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CovidTrackerTheme {
                SplashScreen(
                    onNavigateNextScreen = {
                        startActivity(MainActivity.getIntent(this))
                    },
                )
            }
        }

    }
}
