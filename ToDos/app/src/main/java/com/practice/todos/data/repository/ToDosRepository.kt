package com.practice.todos.data.repository

import com.practice.todos.data.local.model.ToDos
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface ToDosRepository {
    fun getCategories(): List<String>

    fun getCategoriesAsFlow(): Flow<List<ToDos>>

    suspend fun addCategory(toDos: ToDos)

    suspend fun deleteCategory(id: ObjectId)
}