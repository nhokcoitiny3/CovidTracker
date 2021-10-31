package com.tiny.covidtracker.ui.bottom_nav

import com.tiny.covidtracker.R

sealed class NavigationItem (var route:String, var icon: Int, var title:String){
    object Global : NavigationItem("global", R.drawable.ic_global,"Global")
    object VietNam : NavigationItem("vietNam", R.drawable.ic_global,"VietNam")
    object News : NavigationItem("news", R.drawable.ic_news,"News")
    object Health : NavigationItem("health", R.drawable.ic_health,"Health")
    object Declaration : NavigationItem("declaration", R.drawable.ic_declaration,"Declaration")
}