package com.prikhozhayamaria.pmis.deardogs.myapplication.ui.superstructures

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.navigation.NavBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navItems = listOf(
        stringResource(id = R.string.home),
        stringResource(id = R.string.about),
        stringResource(id = R.string.account)
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(200.dp),
                drawerContainerColor = colorResource(R.color.grey_300),
                content = {
                    navItems.forEach { screen ->
                        NavigationDrawerItem(
                            modifier = Modifier.border(width = 1.dp, color = Color.White,  shape = RectangleShape),
                            colors = NavigationDrawerItemDefaults.colors(
                                colorResource(R.color.grey_300),
                                colorResource(R.color.grey_300)
                            ),
                            label = { Text(text = screen, color = Color.DarkGray, fontWeight = FontWeight.W600) },
                            selected = false,
                            onClick = {
                                navController.navigate(screen);
                                scope.launch { drawerState.close() }
                            })
                    }
                }
            )
        },
        content = {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    Surface(elevation = 10.dp){
                        TopAppBar(
                            title = {
                                Text(text = stringResource(R.string.app_name),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontWeight = FontWeight.W700
                                )
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color(0xFF92ACB8)
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "menu",
                                        tint = Color.White
                                    )
                                }

                            }
                        )}
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color(0xFF92ACB8),
                        contentColor = Color(0xFF06364C),
                        modifier = Modifier
                            .height(60.dp)
                    ) {

                        val scope = rememberCoroutineScope()
                        val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }

                        IconButton(onClick = { scope.launch {
                            snackbarHostState.value.showSnackbar("version 1.0")
                        } }) {
                            Icon(Icons.Filled.Info, contentDescription = "info about application")
                        }
                        SnackbarHost(snackbarHostState.value)

                        Spacer(Modifier.weight(1f, true))
//
//                        IconButton(onClick = { }) {
//                            Icon(Icons.Filled.Favorite, contentDescription = "favorite")
//                        }
//
//                        IconButton(onClick = {  }) {
//                            Icon(Icons.Filled.AccountCircle, contentDescription = "account")
//                        }

                    }
                },
                content = {
                        innerPadding ->
                    NavBar(navController, innerPadding)
                }
            )
        }
    )
}
