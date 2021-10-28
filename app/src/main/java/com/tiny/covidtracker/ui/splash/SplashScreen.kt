package com.tiny.covidtracker.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tiny.covidtracker.R
import com.tiny.covidtracker.ui.theme.CovidTrackerTheme
import com.tiny.covidtracker.ui.theme.Pink55
import com.tiny.covidtracker.ui.theme.Violet212
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateNextScreen: () -> Unit) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    //Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        // Customize the delay time
        delay(3000L)
        //      navControler.navigate("main_screen")
    }

    Box(
        modifier = Modifier
            .background(Violet212)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Logo",
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            item {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.ic_virus),
                    contentDescription = "Logo",
                    modifier = Modifier.scale(scale.value)
                )
            }

            item {
                Text(
                    text = stringResource(R.string.str_coronavirus_tracker),
                    fontSize = 24.sp,
                    color = Pink55,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.scale(scale.value)
                )
            }
        }
    }


}

@Preview("SplashScreen")
@Composable
private fun SplashScreenPreview() {
    CovidTrackerTheme {
        SplashScreen(onNavigateNextScreen = {})
    }
}