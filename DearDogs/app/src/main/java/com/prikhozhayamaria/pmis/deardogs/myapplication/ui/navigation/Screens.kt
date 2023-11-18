package com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation

import com.prikhozhayamaria.pmis.deardogs.myapplication.R


sealed class Screen(val screenName: String, val titleResourceId: Int) {
    object Home: Screen("Home page", R.string.home)
    object About: Screen("About application", R.string.about)
    object Account: Screen("Account", R.string.account)

}
