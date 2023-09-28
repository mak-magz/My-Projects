package com.practice.todos

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ToDosDestination {
    val route: String
    val title: String
}

object HomeScreen: ToDosDestination {
    override val route = "todos/home"
    override val title = "Home"
}

object ToDoScreen: ToDosDestination {
    override val route = "todos/detail"
    override val title = "todo"
    const val categoryIdArg = "category_id"
    val routeWithArgs = "${route}/{${categoryIdArg}}"
    val arguments = listOf(
        navArgument(categoryIdArg) { type = NavType.StringType }
    )
}

val toDosScreens = listOf<ToDosDestination>(
    HomeScreen,
    ToDoScreen
)