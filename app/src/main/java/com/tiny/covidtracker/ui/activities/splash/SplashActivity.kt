package com.tiny.covidtracker.ui.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.tiny.covidtracker.ui.activities.main.MainActivity
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
                        finish()
                    },
                )
            }
        }

    }
}
