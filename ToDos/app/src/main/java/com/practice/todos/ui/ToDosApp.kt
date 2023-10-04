package com.practice.todos.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.practice.todos.ui.screens.login.LoginViewModel
import com.practice.todos.ui.screens.todos.ToDosScreen

@Composable
fun ToDosApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    val user = authViewModel.getCurrentUser()!!
    var startDestination = "auth"
    user.let {
        if (user.loggedIn) {
            val initResult = authViewModel.initializeDB(user)

            if (initResult) {
                startDestination = "main"
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
        mainAppGraph(navController = navController)
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

fun NavGraphBuilder.mainAppGraph(navController: NavHostController) {
    navigation(startDestination = HomeScreen.route, route = "main") {
        composable(route = HomeScreen.route) {
            HomeScreen(
                navController = navController
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