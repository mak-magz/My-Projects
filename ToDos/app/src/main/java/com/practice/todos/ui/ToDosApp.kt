package com.practice.todos.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.todos.HomeScreen
import com.practice.todos.ToDoScreen
import com.practice.todos.ui.screens.home.HomeScreen
import com.practice.todos.ui.screens.todos.ToDosScreen

@Composable
fun ToDosApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = ToDoScreen.route) {
            ToDosScreen(
                modifier = Modifier
            )
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
