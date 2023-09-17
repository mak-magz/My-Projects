package com.practice.todos

interface ToDosDestination {
    val route: String
    val title: String
}

object HomeScreen: ToDosDestination {
    override val route = "todos/home"
    override val title = "Home"
}

object ToDoScreen: ToDosDestination {
    override val route = "todos/todo"
    override val title = "todo"
}

val toDosScreens = listOf<ToDosDestination>(
    HomeScreen,
    ToDoScreen
)