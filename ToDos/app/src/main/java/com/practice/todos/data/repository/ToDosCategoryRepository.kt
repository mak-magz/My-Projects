package com.practice.todos.data.repository

interface ToDosCategoryRepository {
    fun getCategories(): List<String>
}