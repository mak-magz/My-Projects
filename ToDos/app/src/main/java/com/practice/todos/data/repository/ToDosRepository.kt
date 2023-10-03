package com.practice.todos.data.repository

import com.practice.todos.data.local.model.ToDos
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface ToDosRepository {
    fun getCategories(): List<String>

    fun getToDos(): Flow<List<ToDos>>

    suspend fun addToDos(toDos: ToDos)

    suspend fun deleteToDos(id: ObjectId)
}