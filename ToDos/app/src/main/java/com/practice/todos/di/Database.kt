package com.practice.todos.di

import com.practice.todos.data.local.model.ToDos
import kotlinx.coroutines.flow.Flow

interface Database {

    suspend fun init(): Boolean

    fun getToDos(): Flow<List<ToDos>>

    suspend fun addToDos(toDos: ToDos)

    suspend fun deleteToDos(id: String)
}