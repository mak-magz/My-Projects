package com.practice.todos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practice.todos.ui.screens.home.HomeScreen


@Composable
fun ToDosNavigation(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
        ) {
        composable( route = HomeScreen.route) {
            HomeScreen(
                "Hello"
            )
        }
    }
}