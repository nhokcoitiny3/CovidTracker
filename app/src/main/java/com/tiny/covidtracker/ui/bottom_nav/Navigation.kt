package com.tiny.covidtracker.ui.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tiny.covidtracker.ui.bottom_nav.item.*

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Global.route) {
        composable(NavigationItem.Global.route) {
            GlobalScreen()
        }
        composable(NavigationItem.VietNam.route) {
            VietNamScreen()
        }
        composable(NavigationItem.News.route) {
            NewsScreen()
        }
        composable(NavigationItem.Health.route) {
            HealthScreen()
        }
        composable(NavigationItem.Declaration.route) {
            DeclarationScreen()
        }
    }
}