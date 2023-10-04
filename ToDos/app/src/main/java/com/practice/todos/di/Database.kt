package com.practice.todos.di

import com.practice.todos.data.local.model.ToDos
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow

interface Database {

    fun init(user: User)

    fun getToDos(): Flow<List<ToDos>>

    suspend fun addToDos(toDos: ToDos)

    suspend fun deleteToDos(id: String)
}