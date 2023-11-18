package com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.DOG_ID_ARG
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.TITLE_ARG
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.DogsDestinationsArgs.USER_MESSAGE_ARG
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.PlanetsScreens.ADD_EDIT_DOGHOME_SCREEN
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.PlanetsScreens.HOME_SCREEN
import java.util.UUID

private object PlanetsScreens {
    const val HOME_SCREEN = "Home page"
    const val ADD_EDIT_DOGHOME_SCREEN = "AddEditDogScreen"
}

/**
 * Arguments used in [PlanetsDestinations] routes
 */
object DogsDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val DOG_ID_ARG = "id"
    const val TITLE_ARG = "title"
}

/**
 * Destinations used in the [MainActivity]
 */
object DogsDestinations {
    const val DOGS_ROUTE = "$HOME_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_EDIT_DOG_ROUTE = "$ADD_EDIT_DOGHOME_SCREEN/{$TITLE_ARG}?$DOG_ID_ARG={$DOG_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class DogsNavigationActions(private val navController: NavHostController) {

    fun navigateToDogs(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            HOME_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToAddEditDog(title: Int, id: UUID?) {
        System.out.println("title is: "+title);
        System.out.println("UUID value in navigation: "+id);
        navController.navigate(
            "${ADD_EDIT_DOGHOME_SCREEN}/$title".let {
                System.out.println(if (id != null) "$it?$DOG_ID_ARG=$id" else it)
                if (id != null) "$it?$DOG_ID_ARG=$id" else it
            }
        )
    }
}