package com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.Account
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.about.AboutApp
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.home.AddEditDogScreen
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.home.HomeScreen
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.DOG_ID_ARG
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.TITLE_ARG
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.USER_MESSAGE_ARG

import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    navController: NavHostController,
    innerPadding: PaddingValues,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = DogsDestinations.DOGS_ROUTE,
    navActions: DogsNavigationActions = remember(navController) {
        DogsNavigationActions(navController)
    }
){
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = DogsDestinations.DOGS_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
            //Screen.Home.screenName
        ) {
            HomeScreen(
                innerPadding,
                navController,
                addDog = {navActions.navigateToAddEditDog( R.string.add_dog, null) },
                editDog = { dogId -> navActions.navigateToAddEditDog(R.string.edit_dog,dogId) }
            )
        }

        composable(
            route= DogsDestinations.ADD_EDIT_DOG_ROUTE,
            arguments = listOf(
                navArgument(TITLE_ARG) { type = NavType.IntType },
                navArgument(DOG_ID_ARG) { type = NavType.StringType; nullable = true },
            )
        ) { entry ->
            val memoryId = entry.arguments?.getString(DOG_ID_ARG)
            AddEditDogScreen(
                innerPadding,
                onDogUpdate = {
                    navActions.navigateToDogs(
                        if (memoryId == null) ADD_EDIT_RESULT_OK else EDIT_RESULT_OK
                    )
                },
            )
        }

        composable(Screen.About.screenName) {
            AboutApp(innerPadding)
        }
        composable(Screen.Account.screenName) {
            Account(innerPadding)
        }

    }
}
// Keys for navigation
const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3
