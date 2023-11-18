package com.prikhozhayamaria.pmis.deardogs.myapplication.ui.superstructures.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels.DogsListViewModel
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.home.HomeScreenContent
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuperstructure(
    innerPadding: PaddingValues,
    controller: NavHostController,
    addDog: () -> Unit,
    editDog: (UUID) -> Unit,
    snackbarHostState: SnackbarHostState = remember {   SnackbarHostState() },
    viewModel: DogsListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        snackbarHost =  { SnackbarHost(snackbarHostState) },
        modifier = Modifier.padding(innerPadding),
        bottomBar = {
            BottomAppBar(
                actions = {
                },
                containerColor = Color.Transparent,
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = addDog,
                        //containerColor = colorResource(R.color.blue_grey_500),
                        //elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                        text = {Text(text = "Add")},
                        icon = {Icon(Icons.Filled.Add, "Localized description")},
                        backgroundColor = colorResource(R.color.blue_grey_500)
                    )
                }

            )
        },
        content = {it-> HomeScreenContent(it, innerPadding, controller, addDog, editDog, uiState) }
    )
}


/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val padding = PaddingValues(0.dp);
    val controller = rememberNavController()
    val navActions: MemoriesNavigationActions = remember(controller) {
        MemoriesNavigationActions(controller)
    }
    HomeSuperstructure(innerPadding = padding, controller = controller,
        addMemory = {
            navActions.navigateToAddEditMemory(
                R.string.add_memory, null) },
        editMemory = {
                memoryId -> navActions.navigateToAddEditMemory(R.string.edit_memory,
            memoryId) })
}

*/