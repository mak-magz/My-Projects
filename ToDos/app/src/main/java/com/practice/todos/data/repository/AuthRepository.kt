package com.practice.todos.data.repository

import com.practice.todos.domain.model.Result
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginAnonymously(): Flow<Result<User>>

    suspend fun logout()

    fun getCurrentUser(): Any
}