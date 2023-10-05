package com.practice.todos.di

import com.practice.todos.domain.model.Result
import io.realm.kotlin.mongodb.AuthenticationChange
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow

interface Auth {
    fun loginAnonymous(): Flow<Result<User>>

    fun getCurrentUser(): User?

    suspend fun logout()

    fun getAuthStateAsFlow(): Flow<AuthenticationChange>
}