package com.practice.todos.data.repository

import com.practice.todos.data.local.model.Category
import kotlinx.coroutines.flow.Flow

interface ToDosCategoryRepository {
    fun getCategories(): List<String>

    fun getCategoriesAsFlow(): Flow<List<Category>>

}