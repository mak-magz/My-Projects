package com.practice.todos.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.practice.todos.HomeScreen
import com.practice.todos.ToDoScreen
import com.practice.todos.ui.screens.home.HomeScreen
import com.practice.todos.ui.screens.login.LoginScreen
import com.practice.todos.ui.screens.todos.ToDosScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDosApp(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val loginUiState by authViewModel.authState.collectAsState()
    AuthStateChecker(authState = loginUiState, navController = navController)

    val user = authViewModel.getCurrentUser()
    var startDestination = "auth"
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home") }, selected = false, onClick = { /*TODO*/ })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val scope = rememberCoroutineScope()
                    OutlinedButton(
                        modifier = Modifier,
                        onClick = {
                            authViewModel.logout()
                            scope.launch {
                                drawerState.close()
                            }
                        }) {
                        Text(
                            text = "LOGOUT",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }
        }
    ) {
        user.let {
            if (user?.loggedIn == true) {
                val initResult = authViewModel.initializeDB(user)

                if (initResult) {
                    startDestination = "main"
                    Log.d("REALM INITIALIZATION: ", "SUCCESS")
                } else {
                    Log.e("REALM INITIALIZATION: ", "FAILED")
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            authGraph(navController = navController)
            mainAppGraph(navController = navController, drawerState = drawerState)
        }
    }

}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToDetails(categoryID: String) {
    val route = "${ToDoScreen.route}/$categoryID"
    Log.d("ROUTE: ", route)
    navigateSingleTopTo(route)
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") {
            LoginScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainAppGraph(navController: NavHostController, drawerState: DrawerState) {
    navigation(startDestination = HomeScreen.route, route = "main") {
        composable(route = HomeScreen.route) {
            HomeScreen(
                navController = navController,
                drawerState = drawerState
            )
        }
        composable(
            route = ToDoScreen.routeWithArgs,
            arguments = ToDoScreen.arguments
        ) {
            val id = it.arguments?.getString(ToDoScreen.categoryIdArg)
            id?.let { categoryID ->
                ToDosScreen(
                    modifier = Modifier,
                    categoryId = categoryID,
                    navController = navController
                )
            }
        }
    }
}

@Composable
internal fun AuthStateChecker(authState: AuthState<*>, navController: NavHostController) {
    when(authState) {
        is AuthState.LoggedIn -> {
            Log.e("STATE", "LOGGED IN")
        }
        is AuthState.LoggedOut -> {
            Log.e("STATE", "LOGGED OUT")
            navController.navigateSingleTopTo("auth")
        }
        is AuthState.Loading -> {
            Log.e("AUTH STATE", "INITIALIZING AUTH STATE . . . ")
        }
    }
}