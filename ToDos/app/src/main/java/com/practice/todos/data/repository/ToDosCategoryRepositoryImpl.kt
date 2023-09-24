package com.practice.todos.data.repository

class ToDosCategoryRepositoryImpl:ToDosCategoryRepository {
    override fun getCategories(): List<String> {
        return listOf("One", "Two")
    }
}