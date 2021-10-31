package com.tiny.covidtracker.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tiny.covidtracker.ui.bottom_nav.BottomNavigationBar
import com.tiny.covidtracker.ui.bottom_nav.Navigation
import com.tiny.covidtracker.ui.theme.CovidTrackerTheme

@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        Navigation(navController)
    }
}

@Preview("HomeScreen")
@Composable
private fun SplashScreenPreview() {
    CovidTrackerTheme {
        HomeScreen()
    }
}